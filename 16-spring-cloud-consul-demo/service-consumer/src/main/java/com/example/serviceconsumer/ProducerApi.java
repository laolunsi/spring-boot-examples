package com.example.serviceconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-producer")
public interface ProducerApi {

    @GetMapping(value = "producer/hello/{name}")
    public String hello(@PathVariable("name") String name);
}
