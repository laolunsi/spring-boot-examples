package com.example.serviceconsumerfeign;

import org.springframework.stereotype.Component;

/**
 * ProducerApi对应的断路器
 * @author zfh
 * @version 1.0
 * @since 2019/8/28 19:01
 */
@Component
public class ProducerApiHystrix implements ProducerApi {

    @Override
    public String hello(String name) {
        return "sorry, " + name + ", this service is unavailable temporarily. We are returning the defaultValue by hystrix.";
    }
}
