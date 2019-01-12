package com.amy.pie.redis.config;

/**
 * @Author : Administrator.
 * @Description :
 * @Date : Created in 2019/1/12 17:28
 * @Modified By :
 */

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * redis 配置项
 */
@Getter
@Setter
@Component
@ConfigurationProperties
public class RedisProperties {
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.pool.min-idle}")
    private int minIdle;
    @Value("${spring.redis.pool.max-wait}")
    private long maxWait;
    @Value("${spring.redis.pool.max-total}")
    private int maxTotal;
    @Value("${spring.redis.sentinel.master}")
    private String masterName;
    @Value("${spring.redis.sentinel.nodes}")
    private String clusterNodes;

}
