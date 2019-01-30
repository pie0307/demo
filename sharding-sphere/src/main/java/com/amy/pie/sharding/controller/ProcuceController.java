package com.amy.pie.sharding.controller;

import com.amy.pie.sharding.domain.Order;
import com.amy.pie.sharding.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;


/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/11 11:35
 */
@RestController
@Slf4j
public class ProcuceController {

    @Resource
    private OrderMapper orderMapper;

    @PostConstruct
    void init() {
        for (int i = 0; i < 100; i++) {
            Order order = new Order();
            order.setUserId(i);
            order.setStatus("1");
            orderMapper.createUser(order);
        }

        List<Order> orders = orderMapper.selectByUserId(50);
        log.info(orders.toString());

    }
}
