package com.example.clientdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/10/16 10:21
 */
@RestController
@RequestMapping(value = "")
public class HelloAction {

    @GetMapping(value = "")
    public String index() {
        return "this is index page, you can request /hello to get other fallback.";
    }

    @GetMapping(value = "hello")
    public String hello() {
        return "hello.";
    }
}
