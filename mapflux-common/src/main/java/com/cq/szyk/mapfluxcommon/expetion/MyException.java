package com.cq.szyk.mapfluxcommon.expetion;

import com.cq.szyk.mapfluxcommon.response.ResultCode;

/**
 * @author lyz
 * @describe 运行时异常类
 * @date 2019/8/1
 */
public class MyException extends RuntimeException {
    private ResultCode resultCode;

    MyException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return this.resultCode;
    }
}
