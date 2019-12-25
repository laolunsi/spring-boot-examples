package com.example.applicationdemo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author zhangfanghao
 * @version 1.0
 * @date 2019-11-24 20:53
 */
@Component
public class MyRunnerForApplication implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("MyRunnerForApplication: " + args);
    }
}
