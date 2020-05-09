package com.example.serviceconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/8/27 14:51
 */
@RestController
@RequestMapping(value = "consumer")
public class TestAction {

    @Autowired
    private HelloRemote helloRemote;

    @GetMapping(value = "test")
    public String consume(String name) {
        String res = helloRemote.hello(name);
        String str = "服务消费者调用服务提供者，提供参数name=" + name + "，获取返回值：" +  res;
        System.out.println(str);
        return str;
    }
}
