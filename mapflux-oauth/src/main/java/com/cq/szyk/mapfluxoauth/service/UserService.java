package com.cq.szyk.mapfluxoauth.service;

import com.cq.szyk.mapfluxmodel.users.Users;

public interface UserService {

    Users findUserByUserName(String userName);
}
