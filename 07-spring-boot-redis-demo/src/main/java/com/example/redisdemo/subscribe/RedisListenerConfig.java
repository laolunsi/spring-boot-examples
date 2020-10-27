package com.example.redisdemo.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * 消息监听器配置
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/10/26 16:52
 */
@Configuration
@EnableCaching
public class RedisListenerConfig {

    @Autowired
    private DirectMsgListener directMsgListener;

    @Autowired
    private PatternMsgListener patternMsgListener;

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // 可以配置不同的消息监听器，处理不同 channel 的消息
        // PatternTopic：匹配以 msg 开头的所有 channel
        container.addMessageListener(patternMsgListener, new PatternTopic("msg*"));
        // ChannelTopic: 直接匹配名称为 msg 的channel
        container.addMessageListener(directMsgListener, new ChannelTopic("msg"));
        return container;
    }

}
