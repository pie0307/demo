package com.amy.pie.camel.disruptor.demo;

import com.amy.pie.camel.disruptor.handler.Handler1;
import com.amy.pie.camel.disruptor.handler.Handler2;
import com.amy.pie.camel.disruptor.handler.Handler3;
import com.amy.pie.camel.disruptor.producer.TradePublisher;
import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        long beginTime = System.currentTimeMillis();
        int bufferSize = 1024;
        ExecutorService executor = Executors.newFixedThreadPool(8);

        Disruptor<Trade> disruptor = new Disruptor<>(new EventFactory<Trade>() {
            @Override
            public Trade newInstance() {
                return new Trade();
            }
        }, bufferSize, executor, ProducerType.SINGLE, new BusySpinWaitStrategy());

        //菱形操作
        //使用disruptor创建消费者组C1,C2
        Handler1 handler1 = new Handler1();
        Handler2 handler2 = new Handler2();
        Handler3 handler3 = new Handler3();
        disruptor.handleEventsWith(handler1, handler2);
        //声明在C1,C2完事之后执行JMS消息发送操作 也就是流程走到C3
        disruptor.after(handler1).handleEventsWith(handler3);

        disruptor.start();//启动
        CountDownLatch latch = new CountDownLatch(1);
        //生产者准备
        executor.submit(new TradePublisher(latch, disruptor));

        latch.await();//等待生产者完事.

        disruptor.shutdown();
        executor.shutdown();
        System.out.println("总耗时:" + (System.currentTimeMillis() - beginTime));
    }
}
