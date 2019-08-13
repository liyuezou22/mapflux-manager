package com.cq.szyk.mapfluxcommon.response;

import lombok.ToString;

/**
 * @author lyz
 * @describe 枚举，统一定义响应结果
 * @date 2019/8/1
 */

@ToString
public enum CommonCode implements ResultCode {
    //1000开始的均为true，2000开始的均为false
    SUCCESS(true, 10000, "操作成功！"),
    REGISTER_SUCCESS(true,10001,"用户注册成功！"),
    LOGIN_SUCCESS(true,1002,"登陆成功！"),
    DATA_NULL(false, 2001, "数据不能为空！"),
    USERNAME_NULL(false,2002,"用户名不能为空！"),
    PASSWORD_NULL(false,2003,"密码不能为空！"),
    TELEPHONE_NULL(false,2004,"手机号不能为空！"),
    EMAIL_NULL(false,2005,"邮箱不能为空！"),
    USERNAME_REPEAT(false,2006,"用户名已存在！"),
    MKDIR_FAILS(false,2007,"用户文件夹生成失败！"),
    REGISTER_FAILS(false,2008,"用户注册失败！"),
    NICKNAME_NULL(false,2009,"昵称不能为空！"),
    PARAMETER_NULL(false,2010,"参数不能为空！"),
    FILE_NULL(false,2011,"上传文件不能为空"),
    NAME_NULL(false,2012,"名称不能为空！"),
    TYPE_NULL(false,2013,"类型不能为空！"),
    TAG_NULL(false,2014,"标签不能为空！"),
    FAILS(false,2015,"操作失败！"),
    GRANT_NULL(false,2016,"登陆类型不能为空！"),
    LOGIN_FAILS(false,2017,"登陆失败，用户名/密码错误！"),
    JTI_SAVE_FAILS(false,2018,"身份令牌存储失败！"),
    NOT_LOGIN(false,2019,"请先进行登陆！"),
    NOT_TOKEN(false,2020,"token已经过期，请重新登陆！");
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    CommonCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}
