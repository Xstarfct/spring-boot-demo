package com.fct.api.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.fct.api.dto.CommonApiResult;
import com.fct.common.constant.ApiConstant;

/**
 * 当未登录或者token失效访问接口时，自定义的返回结果
 *
 * @author xstarfct
 * @date 2019/1/11 10:38 AM
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding(ApiConstant.CHAR_ENCODE_UTF_8);
        response.setContentType(ApiConstant.JSON_CONTENT_TYPE);
        response.getWriter().println(JSON.toJSONString(new CommonApiResult().unauthorized(authException.getMessage())));
        response.getWriter().flush();
    }
}
