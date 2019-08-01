package com.cq.szyk.mapfluxuser.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import java.io.File;

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
        //根据用户名，在当前项目目录下，创建一个文件夹，用于存放用户数据
        createUserDir(user.getUsername());
        //将用户信息存入数据库，完成注册功能
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int insert = userMapper.insert(user);
        if (insert <= 0) {
            ExceptionCast.cast(CommonCode.REGISTER_FAILS);
        }
        return new ResponseResult(CommonCode.REGISTER_SUCCESS);
    }

    //根据用户名，在当前项目目录下，创建一个文件夹，用于存放用户数据
    private void createUserDir(String username) {
        String projectPath = System.getProperty("user.dir");
        String dirPaht = projectPath + "/mapflux_data/" + username;
        File file = new File(dirPaht);
        if (!file.exists()) {
            boolean mkdir = file.mkdir();
            if (!mkdir) {
                LOG.error("registerUser ---> 创建用户文件夹失败！");
                ExceptionCast.cast(CommonCode.MKDIR_FAILS);
            }
        }
    }
}
