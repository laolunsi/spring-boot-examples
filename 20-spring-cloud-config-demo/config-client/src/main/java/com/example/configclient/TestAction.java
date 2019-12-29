package com.example.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangfanghao
 * @version 1.0
 * @date 2019-12-27 22:41
 */
@RestController
@RequestMapping(value = "test")
public class TestAction {

    /**
     * 从配置中心读取配置项：msg
     */
    @Value("${msg}")
    private String msg;

    @GetMapping(value = "hello")
    public String hello() {
        return "hello, " + msg;
    }
}
