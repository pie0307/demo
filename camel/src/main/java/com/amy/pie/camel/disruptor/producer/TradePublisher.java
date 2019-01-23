package com.amy.pie.camel.disruptor.producer;

import com.amy.pie.camel.disruptor.demo.Trade;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/23 17:16
 */
public class TradePublisher implements Runnable {

    Disruptor<Trade> disruptor;
    private CountDownLatch latch;

    private static int LOOP = 1;    // 模拟百万次交易的发生

    public TradePublisher(CountDownLatch latch, Disruptor<Trade> disruptor) {
        this.disruptor = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {
        for (int i = 0; i < LOOP; i++) {
            disruptor.publishEvent(new EventTranslator<Trade>() {
                @Override
                public void translateTo(Trade event, long l) {
                    event.setId(UUID.randomUUID().toString());
                }
            });
        }
        latch.countDown();
    }

}

