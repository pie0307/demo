package com.amy.pie.kafka.produce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 生产者
 * 使用@EnableScheduling注解开启定时任务
 */
@Component
@EnableScheduling
@Slf4j
public class KafkaProducer {

    @Resource
    private KafkaTemplate kafkaTemplate;

    /**
     * 定时任务
     * D:\kafka2.12>.\bin\windows\kafka-server-start.bat .\config\server.properties
     */
    @Scheduled(cron = "00/1 * * * * ?")
    public void send() {
        String message = UUID.randomUUID().toString();
        ListenableFuture future = kafkaTemplate.send("test.topic", "生产消息 " + message);
        future.addCallback(new SuccessCallback() {

            @Override
            public void onSuccess(Object o) {
                log.info("success  ", o);
            }
        }, new FailureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                log.info("error  ", throwable.getMessage());
            }
        });
    }

}
