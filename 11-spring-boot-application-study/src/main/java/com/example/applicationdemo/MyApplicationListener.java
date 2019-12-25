package com.example.applicationdemo;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

/**
 * @author zhangfanghao
 * @version 1.0
 * @date 2019-11-24 00:29
 */
@Order(200)
public class MyApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent applicationEnvironmentPreparedEvent) {
        System.out.println("MyApplicationListener: 应用环境准备完毕" + applicationEnvironmentPreparedEvent.toString());
    }
}
