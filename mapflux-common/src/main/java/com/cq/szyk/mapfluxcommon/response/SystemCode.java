package com.cq.szyk.mapfluxcommon.response;

public class SystemCode implements ResultCode {
    //操作是否成功
    private boolean success;
    //操作代码
    private int code;
    //提示信息
    private String message;
    //响应数据
    private Object data;

    public SystemCode(boolean success, int code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
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

    @Override
    public Object data() {
        return data;
    }
}
