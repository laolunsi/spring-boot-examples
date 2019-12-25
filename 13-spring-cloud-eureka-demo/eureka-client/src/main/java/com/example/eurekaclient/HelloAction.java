package com.example.eurekaclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/8/31 10:26
 */
@RestController
@RequestMapping(value = "")
public class HelloAction {

    @GetMapping(value = "")
    public String hello() {
        return "Hello, this is eureka client-1";
    }
}
