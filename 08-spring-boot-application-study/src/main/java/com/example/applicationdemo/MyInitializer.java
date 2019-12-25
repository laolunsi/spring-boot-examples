package com.example.applicationdemo;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;

/**
 * @author zhangfanghao
 * @version 1.0
 * @date 2019-11-22 23:06
 */
@Order(100)
public class MyInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("上下文初始化器：" + configurableApplicationContext.toString());
    }
}
