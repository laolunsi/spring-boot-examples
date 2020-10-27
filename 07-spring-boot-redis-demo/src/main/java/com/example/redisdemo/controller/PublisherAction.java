package com.example.redisdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/10/26 16:53
 */
@RestController
@RequestMapping("redis")
public class PublisherAction {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("msg")
    public String publishMsg(String msg, String channel) {
        if (StringUtils.isEmpty(msg) || StringUtils.isEmpty(channel)) {
            return "false";
        }
        System.out.println("发布消息【" + msg + "】至 channel: " + channel);
        redisTemplate.convertAndSend(channel, msg);
        return "true";
    }
}
