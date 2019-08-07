package com.cq.szyk.mapfluxoauth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cq.szyk.mapfluxmodel.users.Users;
import com.cq.szyk.mapfluxoauth.mapper.UserMapper;
import com.cq.szyk.mapfluxoauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Users findUserByUserName(String userName) {
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userName);
        return userMapper.selectOne(wrapper);
    }
}
