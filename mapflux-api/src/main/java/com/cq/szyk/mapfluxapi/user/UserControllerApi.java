package com.cq.szyk.mapfluxapi.user;

import com.cq.szyk.mapfluxcommon.response.Response;
import com.cq.szyk.mapfluxmodel.users.Users;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户管理模块接口",description = "用户管理模块接口")
public interface UserControllerApi {
     @ApiOperation(value = "用户注册")
     Response registerUser(Users users);
     @ApiOperation(value = "用户信息编辑")
     Response getUserList(Integer pageNum,Integer pageSize);
     @ApiOperation(value = "用户登陆",notes = "")
     Response userLogin(String username,String password,String grant_type);
}
