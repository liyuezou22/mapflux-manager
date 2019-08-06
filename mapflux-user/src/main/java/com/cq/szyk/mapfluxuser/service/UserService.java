package com.cq.szyk.mapfluxuser.service;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(LoginConfig.class);
    @Resource
    private UserMapper userMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

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
        return new ResponseResult(CommonCode.REGISTER_SUCCESS,user);
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


}
