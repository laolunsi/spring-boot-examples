package com.eknown.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 手动发送消息
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/8/10 16:39
 */
public class ProducerTest {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest"); // 默认用户名和密码都是 guest
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("demo", false, false, false, null);
        for (int i = 0; i < 10; i++) {
            String msg = "你好，我是 eknown" + i;
            System.out.println("发送消息： " + msg);
            channel.basicPublish("", "demo", null, msg.getBytes());
            Thread.sleep(1000);
        }

        // 发送完消息后关闭；如果没有 close，那么程序会一直在运行中
        System.out.println("关闭 MQ 连接");
        channel.close();
        connection.close();

    }
}
