package com.amy.pie.dubbo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author : Administrator.
 * @Description :
 * @Date : Created in 2019/1/5 22:34
 * @Modified By :
 */
@SpringBootApplication
@EnableDubboConfiguration
@ComponentScan(basePackages = {"com.amy.pie.dubbo.provider"})
public class DubboProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }
}
