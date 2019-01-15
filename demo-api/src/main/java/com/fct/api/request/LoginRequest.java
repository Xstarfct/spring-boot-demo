package com.fct.api.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登陆的请求信息
 *
 * @author xstarfct
 * @version 2019/1/11 4:07 PM
 */
@Data
public class LoginRequest implements Serializable {
    private static final long serialVersionUID = -5299060863241036904L;

    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "用户名不能为空")
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    @NotNull(message = "密码不能为空")
    private String password;
}
