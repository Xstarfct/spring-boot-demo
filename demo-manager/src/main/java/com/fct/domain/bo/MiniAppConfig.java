package com.fct.domain.bo;

import java.io.Serializable;

import lombok.Data;

/**
 * 微信小程序配置实体类
 *
 * @author xstarfct
 * @version 2019/1/14 2:43 PM
 */
@Data
public class MiniAppConfig implements Serializable {
    private static final long serialVersionUID = -1049752683242102242L;
    /**
     * 设置微信小程序的appId
     */
    private String appId;
    /**
     * 设置微信小程序的Secret
     */
    private String secret;
    /**
     * 设置微信小程序消息服务器配置的token
     */
    private String token;
    /**
     * 设置微信小程序消息服务器配置的EncodingAESKey
     */
    private String aesKey;
    /**
     * 消息格式，XML或者JSON
     */
    private String msgDataFormat;
}
