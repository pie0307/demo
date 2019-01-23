package com.amy.pie.camel.disruptor.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/23 17:14
 */
@Getter
@Setter
@ToString
public class Trade {
    private String id;//ID
    private String name;
    private double price;//金额
    private AtomicInteger count = new AtomicInteger(0);
}
