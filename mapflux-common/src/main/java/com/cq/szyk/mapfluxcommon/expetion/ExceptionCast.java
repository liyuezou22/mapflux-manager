package com.cq.szyk.mapfluxcommon.expetion;

import com.cq.szyk.mapfluxcommon.response.ResultCode;

/**
 * @author lyz
 * @describe 异常抛出
 * @date 2019/5/27
 */
public class ExceptionCast {

    private ExceptionCast() {

    }

    public static MyException cast(ResultCode resultCode) {
        throw new MyException(resultCode);
    }


}
