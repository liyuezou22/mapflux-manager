package com.cq.szyk.mapfluxuser.inspect;

import com.cq.szyk.mapfluxcommon.expetion.ExceptionCast;
import com.cq.szyk.mapfluxcommon.response.CommonCode;
import com.cq.szyk.mapfluxmodel.users.Users;

/**
 * @author lyz
 * @describe 用于对该模块各个方法中的参数进行非空校验
 * @date 2019/8/1
 */
public class UserModelInspect {
    private UserModelInspect() {

    }

    public static void registerUserInspect(Users user) {
        if (user == null) {
            ExceptionCast.cast(CommonCode.DATA_NULL);
        }
        if (user.getUsername() == null || "".equals(user.getUsername())) {
            ExceptionCast.cast(CommonCode.USERNAME_NULL);
        }
        if (user.getPassword() == null || "".equals(user.getPassword())) {
            ExceptionCast.cast(CommonCode.PASSWORD_NULL);
        }
        if (user.getNickname() == null || "".equals(user.getNickname())) {
            ExceptionCast.cast(CommonCode.NICKNAME_NULL);
        }
        if (user.getEmail() == null || "".equals(user.getEmail())) {
            ExceptionCast.cast(CommonCode.EMAIL_NULL);
        }
        if (user.getTelephone() == null || "".equals(user.getTelephone())) {
            ExceptionCast.cast(CommonCode.TELEPHONE_NULL);
        }
    }

    public static void repeatUserName(Users user) {
        if (user != null) {
            ExceptionCast.cast(CommonCode.USERNAME_REPEAT);
        }
    }

    public static void getUserListInspect(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            ExceptionCast.cast(CommonCode.PARAMETER_NULL);
        }
    }

    public static void userLoginInspect(String userName,String  password,String grant_type){
        if (userName == null || "".equals(userName)) {
            ExceptionCast.cast(CommonCode.USERNAME_NULL);
        }
        if (password == null || "".equals(password)) {
            ExceptionCast.cast(CommonCode.PASSWORD_NULL);
        }
        if (grant_type == null || "".equals(grant_type)) {
            ExceptionCast.cast(CommonCode.GRANT_NULL);
        }
    }
}
