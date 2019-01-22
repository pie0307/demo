package com.amy.pie.kafka.consumer;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/14 17:34
 */

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 * 使用@KafkaListener注解,可以指定:主题,分区,消费组
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"test.topic"})
    public void receive(ConsumerRecord<?, ?> record) {
        System.out.println("topic: " + record.topic());
        System.out.println("key: " + record.key());
        System.out.println("value: " + record.value());
    }
}
