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
    SUCCESS(true, 10000, "操作成功！", null);
    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;
    //响应数据
    Object data;

     CommonCode(boolean success, int code, String message, Object data) {
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
