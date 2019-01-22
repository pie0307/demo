package com.amy.pie.elasticsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/16 11:28
 */
@Configuration
@Slf4j
public class ESConfig {

    @Value("${elasticsearch.ip}")
    private String firstIp;
    @Value("${elasticsearch.port}")
    private String firstPort;
    @Value("${elasticsearch.clusterName}")
    private String clusterName;

    @Bean
    public TransportClient getTransportClient() {
        log.info("ElasticSearch初始化开始。。");
        log.info("要连接的节点1的ip是{}，端口是{}，集群名为{}", firstIp, firstPort, clusterName);
        TransportClient client = null;
        try {
            Settings settings =
                    Settings.builder().put("cluster.name", "backend-es-test")
                            .put("client.transport.sniff", true)
                            .build();
            //创建client
            client =
                    new PreBuiltTransportClient(settings)
                            .addTransportAddress(
                                    new InetSocketTransportAddress(
                                            InetAddress.getByName("10.16.34.208"), 9300));

        } catch (Exception e) {
            e.printStackTrace();
            log.error("ElasticSearch初始化失败：" + e.getMessage(), e);
        }

        return client;
    }
}
