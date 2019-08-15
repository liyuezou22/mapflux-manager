package com.cq.szyk.mapfluxuser.config;

import com.cq.szyk.mapfluxcommon.response.CommonCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
/**
* @author lyz
* @describe 验证无效token和无token的请求
* @date 2019/8/14
*/
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Map<String, Object> map = new LinkedHashMap<>();
        Throwable cause = authException.getCause();
        CommonCode tokenError = CommonCode.TOKEN_ERROR;
        CommonCode notPreauthorize = CommonCode.NOT_PREAUTHORIZE;
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {if(cause instanceof InvalidTokenException) {
            map.put("success",tokenError.success());
            map.put("code", tokenError.code());//401
            map.put("message", tokenError.message());
        }else{
            map.put("success",notPreauthorize.success());
            map.put("code", notPreauthorize.code());//401
            map.put("message", notPreauthorize.message());
        }
            response.setContentType("application/json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), map);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}
