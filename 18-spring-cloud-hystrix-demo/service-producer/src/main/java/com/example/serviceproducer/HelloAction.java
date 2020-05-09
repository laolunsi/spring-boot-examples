package com.example.serviceproducer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/8/28 10:01
 */
@RestController
@RequestMapping(value = "producer")
public class HelloAction {

    @GetMapping(value = "hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return "hello, [" + name + "], this is service producer by consul.....";
    }
}
