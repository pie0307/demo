package com.amy.pie.mongodb.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/3/2 16:37
 */
@Slf4j
@Controller
public class ZkController {

    /**
     * zk回话超时时间
     */
    static final int SESSION_TIMEOUT = 60 * 1000;

    /**
     * zk连接超时时间
     */
    static final int CONNECTION_TIMEOUT = 3 * 1000;
    private static final String NAMESPACE = "ehr-job";
  //  private static final String SERVICES = "10.16.37.112:3181";
  private static final String SERVICES = "localhost:2181";

    CuratorFramework client;

    @PostConstruct
    void init() throws Exception {
        client = CuratorFrameworkFactory.builder()
                .connectString(SERVICES)
                .sessionTimeoutMs(SESSION_TIMEOUT)
                .connectionTimeoutMs(CONNECTION_TIMEOUT)
                .retryPolicy(new ExponentialBackoffRetry(1000, 16, 60000))
                .build();
        client.start();

        List<String> strings = client.getChildren().forPath("/ehr-job");

        log.info(strings.toString());

        Void aVoid = client.delete()
                .guaranteed()  // 如果删除失败，那么在后端还是会继续删除，直到成功
                .deletingChildrenIfNeeded()  // 子节点也一并删除，也就是会递归删除
                .forPath("/ehr-job");

        log.info(aVoid.toString());

    }


}
