package com.eknown.producer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/8/10 16:48
 */
public class ConsumerTest {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("consumerTag: " + consumerTag);
                System.out.println("exchange: " + envelope.getExchange());
                System.out.println("routingKey: " + envelope.getRoutingKey());
                System.out.println("deliveryTag: " + envelope.getDeliveryTag());
                System.out.println("接收消息：" + new String(body));

                channel.basicAck(envelope.getDeliveryTag(), false);
                System.out.println("----------------------");
            }
        };

        channel.basicConsume("demo", consumer);
    }
}
