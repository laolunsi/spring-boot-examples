package com.example.serviceproducer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/8/29 16:55
 */
@RestController
@RequestMapping(value = "producer")
public class HelloAction {

    @GetMapping(value = "hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello " + name + ", this is response from hello by service-producer.";
    }
}
