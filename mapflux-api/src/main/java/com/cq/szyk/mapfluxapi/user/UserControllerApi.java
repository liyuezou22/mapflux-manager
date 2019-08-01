package com.cq.szyk.mapfluxapi.user;

import com.cq.szyk.mapfluxcommon.response.Response;
import com.cq.szyk.mapfluxmodel.users.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户管理模块接口",description = "用户管理模块接口")
public interface UserControllerApi {
     @ApiOperation(value = "用户注册",notes = "用户注册")
     Response registerUser(Users user);
}
