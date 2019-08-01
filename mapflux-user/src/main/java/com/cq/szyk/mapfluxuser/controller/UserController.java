package com.cq.szyk.mapfluxuser.controller;

import com.cq.szyk.mapfluxapi.user.UserControllerApi;
import com.cq.szyk.mapfluxcommon.response.Response;
import com.cq.szyk.mapfluxmodel.users.Users;
import com.cq.szyk.mapfluxuser.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController implements UserControllerApi {
    @Resource(name = "userService")
    private UserService userService;

    @PostMapping("/register")
    @Override
    public Response registerUser(Users user) {
        return userService.registerUser(user);
    }
}
