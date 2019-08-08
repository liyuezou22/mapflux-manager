package com.cq.szyk.mapfluxoauth.service;

import com.cq.szyk.mapfluxmodel.users.Users;

public interface UserService {
    /**
     * 根据UserName查询User信息
     * */
    Users findUserByUserName(String userName);
}
