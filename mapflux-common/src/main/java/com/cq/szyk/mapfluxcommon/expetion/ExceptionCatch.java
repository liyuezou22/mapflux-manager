package com.cq.szyk.mapfluxcommon.expetion;

import com.cq.szyk.mapfluxcommon.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author lyz
 * @describe 全局异常捕获
 * @date 2019/5/24
 */
@ControllerAdvice
public class ExceptionCatch {
    private static final Logger LOGGER = LoggerFactory.getLogger(Exception.class);

    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Response myException(MyException e) {
        LOGGER.error(e.getMessage());
        ResultCode resultCode = e.getResultCode();
        return new ResponseResult(resultCode);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response exception(Exception e) {
        LOGGER.error(e.getMessage());
        if("不允许访问".equals(e.getMessage())){
            CustomizeCode customizeCode = new CustomizeCode(false, 2000, "用户权限不足！");
            return new ResponseResult(customizeCode);
        }
        CustomizeCode customizeCode = new CustomizeCode(false, 2000, e.getMessage());
        return new ResponseResult(customizeCode);
    }
}
