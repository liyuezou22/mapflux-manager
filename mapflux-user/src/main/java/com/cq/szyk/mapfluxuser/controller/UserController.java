package com.cq.szyk.mapfluxuser.controller;

import com.cq.szyk.mapfluxapi.user.UserControllerApi;
import com.cq.szyk.mapfluxcommon.response.Response;
import com.cq.szyk.mapfluxmodel.users.Users;
import com.cq.szyk.mapfluxuser.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/getUserList/{pageNum}/{pageSize}")
    @Override
    public Response getUserList(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        return userService.getUserList(pageNum, pageSize);
    }

    @PostMapping("/userLogin")
    @Override
    public Response userLogin(String username, String password, String grant_type) {
        return userService.userLogin(username,password,grant_type);
    }


}
