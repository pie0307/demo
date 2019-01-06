package com.amy.pie.dubbo.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.amy.pie.dubbo.intef.User;
import com.amy.pie.dubbo.intef.UserService;
import org.springframework.stereotype.Component;

/**
 * @Author : Administrator.
 * @Description :
 * @Date : Created in 2019/1/5 22:24
 * @Modified By :
 */
@Service(interfaceClass = UserService.class)
@Component
public class UserServiceImpl implements UserService {
    @Override
    public User saveUser(User user) {
        System.out.println(user.toString());
        return user;
    }
}
