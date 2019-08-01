package com.cq.szyk.mapfluxcommon.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author lyz
 * @describe 统一响应类
 * @date 2019/8/1
 */
@Data
@ToString
@NoArgsConstructor
public class ResponseResult implements Response {

    //操作是否成功
    boolean success;

    //操作代码
    int code;

    //提示信息
    String message;

    //响应数据
    Object data;

    public ResponseResult(ResultCode resultCode) {
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = resultCode.data();
    }
}
