package com.example.serviceconsumerribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/8/28 15:23
 */
@RestController
@RequestMapping(value = "consumer/ribbon")
public class ConsumerAction {

    @Autowired
    private RestTemplate restTemplate;

    private static final String service_producer_name = "service-producer";

    @GetMapping(value = "test")
    @HystrixCommand(fallbackMethod = "testHystrix")
    public String test(String name) {
        String producerRes = restTemplate.getForObject(
                "http://" + service_producer_name + "/producer/hello/" + name, String.class);
        String res = "测试consumer/test接口，基于ribbon调取服务server-producer的hello接口，返回：" + producerRes;
        System.out.println(res);
        return res;
    }

    /**
     * test接口的断路器
     * @param name
     * @return
     */
    private String testHystrix(String name) {
        return "sorry, " + name + ", this service is unavailable temporarily. We are returning the defaultValue by hystrix.";
    }
}
