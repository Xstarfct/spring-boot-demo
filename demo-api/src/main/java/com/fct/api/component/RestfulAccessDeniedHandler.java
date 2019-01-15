package com.fct.api.component;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.fct.api.dto.CommonApiResult;
import com.fct.common.constant.ApiConstant;

/**
 * 当访问接口没有权限时，自定义的返回结果
 *
 * @author Xstarfct
 * @date 2019/1/11 10:38 AM
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding(ApiConstant.CHAR_ENCODE_UTF_8);
        response.setContentType(ApiConstant.JSON_CONTENT_TYPE);
        response.getWriter().println(JSON.toJSONString(new CommonApiResult().forbidden(e.getMessage())));
        response.getWriter().flush();
    }
}
