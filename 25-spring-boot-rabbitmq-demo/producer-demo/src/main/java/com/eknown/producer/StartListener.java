package com.eknown.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 该启动类用于：启动自动推送数据定时任务等
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/8/24 15:33
 */
@Component
public class StartListener implements ApplicationListener<ContextRefreshedEvent> {
    private static boolean isInit = false;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!isInit) {
            System.out.println("系统初始化...");
            //new IncrementThread(rabbitTemplate).start();
            isInit = true;
        }
    }
}
