package com.eknown.producer;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/8/10 17:04
 */
@RestController
@RequestMapping(value = "msg")
public class HelloAction {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping(value = "direct")
    public String sayHello(String msg) {
        // 在默认情况下，如果 routingKey 没有找到对应的 queue，消息会被丢掉
        rabbitTemplate.convertAndSend("demo", msg);
        return "消息发送成功";
    }

    @GetMapping(value = "broadCast")
    public String broadCast(String msg) {
        // 广播消息到 demoex_public 这个 exchange 绑定的所有队列
        rabbitTemplate.convertAndSend("demoex_fanout", "", msg);
        return "广播成功";
    }

    @GetMapping(value = "pattern")
    public String pattern(String msg) {
        rabbitTemplate.convertAndSend("demoex_topic", "demo.a.b.c", msg);
        return "模式传播成功";
    }
}
