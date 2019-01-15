package com.fct.api.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import lombok.Data;

/**
 * API 返回的信息
 *
 * @author xstarfct
 * @version 2019/1/11 10:38 AM
 */
@Data
public class CommonApiResult {
    /**
     * 操作成功
     */
    public static final Integer SUCCESS         = 200;
    /**
     * 操作失败
     */
    public static final Integer FAILED          = 500;
    /**
     * 参数校验失败
     */
    public static final Integer VALIDATE_FAILED = 404;
    /**
     * 未认证
     */
    public static final Integer UNAUTHORIZED    = 401;
    /**
     * 未授权
     */
    public static final Integer FORBIDDEN       = 403;

    private Integer code;
    private String  message;
    private Object  data;

    /**
     * 普通成功返回
     *
     * @param data 获取的数据
     */
    public CommonApiResult success(Object data) {
        this.code = SUCCESS;
        this.message = "操作成功";
        this.data = data;
        return this;
    }

    /**
     * 返回分页成功数据
     */
    public CommonApiResult pageSuccess(List data) {
        PageInfo pageInfo = new PageInfo(data);
        Map<String, Object> result = new HashMap<>();
        result.put("pageSize", pageInfo.getPageSize());
        result.put("totalPage", pageInfo.getPages());
        result.put("total", pageInfo.getTotal());
        result.put("pageNum", pageInfo.getPageNum());
        result.put("list", pageInfo.getList());
        this.code = SUCCESS;
        this.message = "操作成功";
        this.data = result;
        return this;
    }

    /**
     * 普通失败提示信息
     */
    public CommonApiResult failed() {
        this.code = FAILED;
        this.message = "操作失败";
        return this;
    }

    /**
     * exception
     */
    public CommonApiResult exception(Throwable throwable) {
        this.code = FAILED;
        this.message = "内部异常 :" + throwable.getMessage();
        this.data = throwable.getStackTrace()[0];
        return this;
    }

    /**
     * 参数验证失败使用
     *
     * @param message 错误信息
     */
    public CommonApiResult validateFailed(String message) {
        this.code = VALIDATE_FAILED;
        this.message = message;
        return this;
    }

    /**
     * 未登录时使用
     *
     * @param message 错误信息
     */
    public CommonApiResult unauthorized(String message) {
        this.code = UNAUTHORIZED;
        this.message = "暂未登录或token已经过期";
        this.data = message;
        return this;
    }

    /**
     * 未授权时使用
     *
     * @param message 错误信息
     */
    public CommonApiResult forbidden(String message) {
        this.code = FORBIDDEN;
        this.message = "没有相关权限";
        this.data = message;
        return this;
    }

    /**
     * 参数验证失败使用
     * @param result 错误信息
     */
    public CommonApiResult validateFailed(BindingResult result) {
        validateFailed(result.getFieldError().getDefaultMessage());
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
