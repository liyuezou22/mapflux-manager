package com.cq.szyk.mapfluxcommon.response;

/**
 * @author lyz
 * @describe 统一响应接口
 * @date 2019/8/1
 */
public interface ResultCode {
    //操作是否成功,true为成功，false操作失败
    boolean success();

    //操作代码
    int code();

    //提示信息
    String message();


}
