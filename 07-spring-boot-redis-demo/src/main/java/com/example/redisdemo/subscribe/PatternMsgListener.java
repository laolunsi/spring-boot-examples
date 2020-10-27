package com.example.redisdemo.subscribe;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 模式匹配的消息监听器
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/10/26 17:16
 */
@Component
public class PatternMsgListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String content = new String(message.getChannel()) + " : " + new String(message.getBody());
        System.out.println("PatternMsgListener: " + content);
    }
}
