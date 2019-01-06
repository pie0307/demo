package com.amy.pie.dubbo.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.amy.pie.dubbo.intef.User;
import com.amy.pie.dubbo.intef.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : Administrator.
 * @Description :
 * @Date : Created in 2019/1/5 23:03
 * @Modified By :
 */
@RestController
@RequestMapping("/hello/")
public class ConsumerController {

    @Reference
    private UserService uerService;

    @GetMapping
    public User sayHello() {
        User u = new User();
        u.setId(1);
        u.setUsername("test");
        return uerService.saveUser(u);
    }
}
