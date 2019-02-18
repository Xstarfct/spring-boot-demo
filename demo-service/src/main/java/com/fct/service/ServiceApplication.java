package com.fct.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 服务启动类
 *
 * @author xstarfct
 * @version 2019/1/15 11:10 AM
 */
@SpringBootApplication
@MapperScan({"com.fct.core.dao"})
@EnableTransactionManagement
public class ServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}
