package com.fct.manager.miniapp;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import com.fct.domain.bo.MiniAppConfig;

import lombok.Data;

/**
 *
 * @author xstarfct
 * @version 2019/1/14 2:44 PM
 */
@Data
@ConfigurationProperties(prefix = "wx.miniapp")
@PropertySource("classpath:META-INF/mini-app.properties")
class WxMaProperties {
    List<MiniAppConfig> configs;
}
