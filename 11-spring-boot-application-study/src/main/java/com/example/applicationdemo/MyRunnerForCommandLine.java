package com.example.applicationdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author zhangfanghao
 * @version 1.0
 * @date 2019-11-24 20:54
 */
@Component
public class MyRunnerForCommandLine implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyRunnerForCommandLine: " + args);
    }
}
