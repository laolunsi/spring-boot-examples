package com.eknown.producer;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/8/10 17:27
 */
@Configuration
public class RabbitConfig {

    /**
     * 自定义一个默认的 queue
     * 当程序尝试向 RabbitMQ Server 发送消息时，会自动 declare 这个队列；
     * 如果队列不存在，那么创建；如果已存在，比对参数，如果参数相同，没有任何影响；如果参数不同，则报错；
     * @return
     */
    @Bean
    public Queue demo() {
        return new Queue("demo", true);
    }

/*    @Bean
    public Exchange demoEx() {
        return ExchangeBuilder.topicExchange("demoex").build();
    }*/
}
