package com.example.serviceconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/8/29 17:15
 */
@RestController
@RequestMapping(value = "consumer")
public class ConsumerAction {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "test")
    public String test(String name) {
        // 利用RestTemplate，直接请求对应的service-name/request
        String producerRes = restTemplate.getForObject("http://service-producer/producer/hello/" + name, String.class);
        String res = "service-consumer服务调用service-producer服务，hello接口返回数据：" + producerRes;
        System.out.println(res);
        return res;
    }
}
