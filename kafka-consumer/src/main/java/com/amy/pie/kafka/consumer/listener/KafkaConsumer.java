package com.amy.pie.kafka.consumer.listener;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/14 17:34
 */

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 * 使用@KafkaListener注解,可以指定:主题,分区,消费组
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"test.topic"})
    public void receive(String message) {
        System.out.println("test.topic--消费消息:" + message);
    }
}
