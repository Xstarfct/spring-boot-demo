package com.fct.api.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alibaba.fastjson.JSON;
import com.fct.api.dto.CommonApiResult;

import lombok.extern.slf4j.Slf4j;

/**
 * Springboot对Controller层方法进行统一异常处理
 *
 * @author xstarfct
 * @version 2019/1/15 5:11 PM
 */
@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandleAdvice {

    @ExceptionHandler
    public CommonApiResult handler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.info("some exception happened. request={} ", JSON.toJSONString(request.getParameterMap()), e);

        if (response.getStatus() == HttpStatus.BAD_REQUEST.value()) {
            log.info("修改返回状态值为200");
            response.setStatus(HttpStatus.OK.value());
        }
        return new CommonApiResult().exception(e);
    }
}
