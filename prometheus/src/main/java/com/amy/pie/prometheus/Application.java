package com.amy.pie.prometheus;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/3/14 14:52
 */
@SpringBootApplication
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
public class Application  {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
        logger.info("项目启动 ");
    }
}
