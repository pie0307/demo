package com.amy.pie.redis.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.google.common.collect.Sets;
import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 * Redis连接管理类
 */
@Data
@Configuration
@EnableConfigurationProperties({RedisProperties.class})
public class RedisConfig {
    @Resource
    private RedisProperties redisProperties;

    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisProperties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisProperties.getMaxIdle());
        jedisPoolConfig.setMinIdle(redisProperties.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getMaxWait());
        return jedisPoolConfig;
    }

    @Bean(destroyMethod = "destroy")
    public JedisConnectionFactory connectionFactory() {
        RedisSentinelConfiguration redisSentinelConfiguration =
                new RedisSentinelConfiguration(redisProperties.getMasterName(),
                        Sets.newHashSet(redisProperties.getClusterNodes().split(",")));
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisSentinelConfiguration, jedisPoolConfig());
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory());
        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
        // 建议使用这种方式，小范围指定白名单 (1.2.24以及之前版本存在远程代码执行高危安全漏洞)
        ParserConfig.getGlobalInstance().addAccept("com.ziroom.");
        // 设置键（key）的序列化采用StringRedisSerializer
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // 设置值（value）的序列化采用FastJsonRedisSerializer
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        return redisTemplate;
    }


}
