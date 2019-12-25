package com.example.serviceconsumerfeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/8/28 15:23
 */
@RestController
@RequestMapping(value = "consumer/feign")
public class ConsumerAction {

    @Autowired
    private ProducerApi producerApi;

    @GetMapping(value = "test")
    public String test(String name) {
        String producerRes = producerApi.hello(name);
        String res = "测试consumer/test接口，基于feign调取服务server-producer的hello接口，返回：" + producerRes;
        System.out.println(res);
        return res;
    }
}
