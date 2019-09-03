package com.cq.szyk.mapfluxuser.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cq.szyk.mapfluxcommon.expetion.ExceptionCast;
import com.cq.szyk.mapfluxcommon.response.CommonCode;
import com.cq.szyk.mapfluxcommon.response.Response;
import com.cq.szyk.mapfluxcommon.response.ResponseResult;
import com.cq.szyk.mapfluxmodel.users.Users;
import com.cq.szyk.mapfluxuser.inspect.UserModelInspect;
import com.cq.szyk.mapfluxuser.mapper.UserMapper;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(LoginConfig.class);
    @Resource
    private UserMapper userMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private LoadBalancerClient loadBalancerClient;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 用户注册
     */
    public Response registerUser(Users user) {
        //非空校验
        UserModelInspect.registerUserInspect(user);
        //检验账户是否存在
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("username", user.getUsername());
        Users userToUserName = userMapper.selectOne(wrapper);
        UserModelInspect.repeatUserName(userToUserName);
        //将用户信息存入数据库，完成注册功能
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int insert = userMapper.insert(user);
        if (insert <= 0) {
            ExceptionCast.cast(CommonCode.REGISTER_FAILS);
        }
        return new ResponseResult(CommonCode.REGISTER_SUCCESS, user);
    }

    /**
     * 获取用户列表
     */
    public Response getUserList(Integer pageNum, Integer pageSize) {
        //校验参数是否为空
        UserModelInspect.getUserListInspect(pageNum, pageSize);
        //查询列表
        Page<Users> page = new Page<>(pageNum, pageSize);
        IPage<Users> users = userMapper.selectPage(page, new QueryWrapper<>());
        Map<String, Object> map = new HashMap<>();
        map.put("total", users.getTotal());
        map.put("list", users.getRecords());
        return new ResponseResult(CommonCode.SUCCESS, map);
    }


    public Response userLogin(String userName, String password, String grant_type) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String oauthUrl = loadBalancerClient.choose("MAPFLUX-OAUTH").getUri() + "/oauth/token";
        //参数非空校验
        UserModelInspect.userLoginInspect(userName, password, grant_type);
        //设置Authorization,就是client验证(如何传递client信息？ 需要将client的账号密码转换为base64，在前面拼接上"Basic"即可)
        LinkedMultiValueMap<String, String> header = new LinkedMultiValueMap<>();
        String httpBasic = getHttpBasic("client", "client");
        header.add("Authorization", httpBasic);

        //定义body
        LinkedMultiValueMap<Object, Object> body = new LinkedMultiValueMap<>();
        body.add("grant_type", grant_type);
        body.add("username", userName);
        body.add("password", password);

        HttpEntity<Object> httpEntity = new HttpEntity<>(body, header);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }
            }
        });

        ResponseEntity<Map> exchange = restTemplate.exchange(oauthUrl, HttpMethod.POST, httpEntity, Map.class);
        int statusCode = exchange.getStatusCodeValue();
        if (statusCode == 400) {
            return new ResponseResult(CommonCode.LOGIN_FAILS);
        }
        Map map = exchange.getBody();
        JSONObject result = new JSONObject(map);
        //校验成功后，将token存入redis，jti身份令牌做key
        String access_token = result.get("access_token").toString();
        String jti = result.get("jti").toString();
        //设置token 有效期为一天
        redisTemplate.opsForValue().set(jti, access_token, 1, TimeUnit.DAYS);
        Object o = redisTemplate.opsForValue().get(jti);
        if (o == null) {
            ExceptionCast.cast(CommonCode.JTI_SAVE_FAILS);
        }
        //将身份令牌存入cookie
        Cookie cookie = new Cookie("jti", jti);
        response.addCookie(cookie);
        return new ResponseResult(CommonCode.LOGIN_SUCCESS, result);
    }

    public Users getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        if (name == null) {
            ExceptionCast.cast(CommonCode.NOT_LOGIN);
        }
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("username", name);
        Users users = userMapper.selectOne(wrapper);
        return users;
    }

    //获取http basic的串
    private String getHttpBasic(String clientId, String clientSecret) {
        String string = clientId + ":" + clientSecret;
        //将串进行base64编码
        byte[] encode = Base64Utils.encode(string.getBytes());
        return "Basic " + new String(encode);
    }


}
