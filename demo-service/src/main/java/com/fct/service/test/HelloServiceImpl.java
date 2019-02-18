package com.fct.service.test;

import org.apache.dubbo.config.annotation.Service;

/**
 * @author xstarfct
 * @version 2019/1/15 11:47 AM
 */
@Service(version = "${demo.service.version}")
public class HelloServiceImpl implements HelloService {

    /**
     * 打招呼
     *
     * @param name
     * @return
     */
    @Override
    public String sayHello(String name) {
        return String.format("[%s] : Hello, %s", "from ", name);
    }
}
