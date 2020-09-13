package com.eknown.consumerdemo;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/8/11 13:57
 */
@Component
public class RabbitConsumer {

    @RabbitListener(queues = {"demo"})
    public void consume(Message message, Channel channel) throws IOException {
        //System.out.println("接收到消息：" + message);
        String msg = new String(message.getBody());
        System.out.println("接收到正常消息：" + msg);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    // 死信队列使用示例，如需要测试可以放开注释，并把上面的 consume 方法注释掉。
/*    @RabbitListener(queues = {"demo"})
    public void consume2(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());
        if (msg.contains("dead")) {
            System.out.println("拒绝包含dead的消息：" + msg);
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        } else {
            System.out.println("接收到正常消息：" + msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }

    @RabbitListener(queues = {"demo_dead"})
    public void consumeDead(Message message, Channel channel) throws IOException {
        System.out.println("死信队列消息：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }*/

    // 模式匹配
    /*@RabbitListener(queues = {"pat_demo2"})
    public void consume2(Message message, Channel channel) throws IOException {
        System.out.println("接收到消息2：" + message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    // 广播
    @RabbitListener(queues = {"pub_demo1"})
    public void consume3(Message message, Channel channel) throws IOException {
        System.out.println("接收到消息3：" + message);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }*/

    // @Payload 使用
/*  @RabbitListener(queues = {"demo"})
    public void consume(@Payload String body, Channel channel) throws IOException {
        System.out.println("接收到消息：" + body);
    }*/

}
