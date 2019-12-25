package com.example.serviceconsumerfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 调用service-producer服务接口
 */
@FeignClient(name = "service-producer", fallback = ProducerApiHystrix.class)
public interface ProducerApi {

    @GetMapping(value = "producer/hello/{name}")
    public String hello(@PathVariable("name") String name);
}
