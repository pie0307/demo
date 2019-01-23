package com.amy.pie.camel.disruptor.handler;

import com.amy.pie.camel.disruptor.demo.Trade;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * 第三个 Handler2，处理订单信息
 */
public class Handler3 implements EventHandler<Trade>, WorkHandler<Trade> {

    @Override
    public void onEvent(Trade event, long sequence, boolean endOfBatch) throws Exception {
        onEvent(event);
    }

    @Override
    public void onEvent(Trade event) throws Exception {
        long threadId = Thread.currentThread().getId();     // 获取当前线程id
        String id = event.getId();                          // 获取订单号
        System.out.println(String.format("%s：Thread Id %s 订单信息 %s 处理中 ....",
                this.getClass().getSimpleName(), threadId, id));
    }
}
