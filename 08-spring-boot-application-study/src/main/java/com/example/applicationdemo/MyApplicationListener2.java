package com.example.applicationdemo;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

/**
 * @author zhangfanghao
 * @version 1.0
 * @date 2019-11-24 00:34
 */
@Order(201)
public class MyApplicationListener2 implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        System.out.println("MyApplicationListener2: 应用环境准备完毕" + applicationEnvironmentPreparedEvent.toString());
    }
}
