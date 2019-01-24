package com.amy.pie.camel.aggregation;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/24 15:53
 */
@Slf4j
public class MyAggregationStrategy implements AggregationStrategy {

    // @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        log.info("自定义聚合...");
        if (oldExchange == null) {
            return newExchange;
        } else {
            String body1 = oldExchange.getIn().getBody(String.class);
            String body2 = newExchange.getIn().getBody(String.class);
            // 合并
            String merged = (body1 == null) ? body2 : body1 + "," + body2;
            log.info("自定义聚合后的数据：" + merged);
            oldExchange.getOut().setBody(merged);
            return oldExchange;
        }
    }

}
