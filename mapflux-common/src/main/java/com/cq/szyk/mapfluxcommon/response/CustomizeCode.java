package com.cq.szyk.mapfluxcommon.response;
/**
* @author lyz
* @describe 自定义返回类,自定义返回码，均为3000开头
* @date 2019/8/1
*/
public class CustomizeCode implements ResultCode {
    //操作是否成功
    private boolean success;
    //操作代码
    private int code;
    //提示信息
    private String message;

    public CustomizeCode(boolean success, int code, String message) {
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
