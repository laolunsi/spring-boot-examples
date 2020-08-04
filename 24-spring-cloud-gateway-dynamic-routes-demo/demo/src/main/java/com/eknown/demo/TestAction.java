package com.eknown.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/7/20 16:39
 */
@RestController
@RequestMapping(value = "test")
public class TestAction {

    @GetMapping(value = "hello")
    public String hello(String name) {
        System.out.println("hello, " + name);
        return "hello, " + name;
    }
}
