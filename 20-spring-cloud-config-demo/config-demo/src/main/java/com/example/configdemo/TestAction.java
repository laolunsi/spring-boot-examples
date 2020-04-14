package com.example.configdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/4/3 16:15
 */
@RestController
@RequestMapping(value = "test")
public class TestAction {
    @Value("${msg}")
    private String msg;

    @GetMapping(value = "")
    public String msg() {
        return msg;
    }
}
