package com.cq.szyk.mapfluxgetway.fileter;

import com.alibaba.fastjson.JSONObject;
import com.cq.szyk.mapfluxcommon.expetion.ExceptionCast;
import com.cq.szyk.mapfluxcommon.response.CommonCode;
import com.cq.szyk.mapfluxcommon.response.ResultCode;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Component
public class UserLoginFilter extends ZuulFilter {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 存储不拦截的路径
     * */
    private List<String> list = new ArrayList<>();

    {
        list.add("/user/userLogin");
    }

    /**
     * 设置过滤器类型
     * pre：路由之前进行拦截
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 权重
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否开启过滤器
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 登陆认证
     */
    @Override
    public Object run(){
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String requestURI = request.getRequestURI();
        List<String> list2 = list;
        if(list2.contains(requestURI)){
            return null;
        }
        //获取cookie中的jti身份令牌
        String jti = getJti();
        //查询redis，获取token
        String accessToken = getAccessToken(jti);
        //获取请求头中的token
        String accessTokenForHead = getAccessTokenForHead();
        //验证token是否正确
        if (!accessToken.equals(accessTokenForHead)) {
            commonResponse(CommonCode.NOT_TOKEN);
        }
        return null;
    }

    /**
     * 从cookie中获取jti身份令牌
     */
    private String getJti() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Cookie[] cookies = request.getCookies();
        String jti = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("jti")) {
                jti = cookie.getValue();
                break;
            }
        }
        if (jti == null) {
            commonResponse(CommonCode.NOT_LOGIN);
        }
        return jti;
    }

    /**
     * 从redis中获取token
     */
    private String getAccessToken(String jti) {
        Object o = redisTemplate.opsForValue().get(jti);
        if (o == null) {
            commonResponse(CommonCode.NOT_TOKEN);
            return null;
        }else{
            return o.toString();
        }
    }

    /**
     * 从请求头中获取token
     */
    private String getAccessTokenForHead() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            ExceptionCast.cast(CommonCode.NOT_LOGIN);
        }
        String[] s = authorization.split(" ");
        return s[1];
    }

    private void commonResponse(ResultCode resultCode){
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();
        //拒绝访问
        requestContext.setSendZuulResponse(false);
        //设置响应代码
        requestContext.setResponseStatusCode(200);
        //构建响应信息
        HashMap<String, Object> map = new LinkedHashMap<>();
        map.put("success",resultCode.success());
        map.put("code",resultCode.code());
        map.put("message",resultCode.message());
        requestContext.setResponseBody(map.toString());
        //转成json，设置contentType
        response.setContentType("application/json;charset=utf-8");
    }
}
