package com.fct.service.test;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * @author xstarfct
 * @version 2019/1/15 11:47 AM
 */
@Service(version = "${demo.service.version}")
public class HelloServiceImpl implements HelloService {

    /**
     * 打招呼
     * The default value of ${dubbo.application.name} is ${spring.application.name}
     *
     * @param name
     * @return
     */
    @Override
    public String sayHello(String name) {
        return String.format("[%s] : Hello, %s", "from ", name);
    }
}
