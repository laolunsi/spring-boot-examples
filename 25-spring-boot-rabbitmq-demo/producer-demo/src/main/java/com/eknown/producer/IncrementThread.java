package com.eknown.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 定时推送数据，用于测试 RabbitMQ 队列的顺序消费
 *
 * 测试结果：RabbitMQ 所有类型的 exchange 对应的队列，单个队列中的数据是 FIFO （先进先出）的
 * @author zhangfanghao
 * @version 1.0
 * @since 2020/8/24 15:35
 */
public class IncrementThread extends Thread {

    private RabbitTemplate rabbitTemplate;

    public IncrementThread(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public void run() {
        int i = 0;
        System.out.println("启动【递增数据推送线程】");
        while (true) {
            try {
                i = i + 1;
                System.out.println("推送数据：" + i);
                rabbitTemplate.convertAndSend("demoex_fanout", "", "ID: " + i);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
