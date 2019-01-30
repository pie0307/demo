package com.amy.pie.camel.processor;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/23 15:47
 */

import com.alibaba.fastjson.JSON;
import com.amy.pie.camel.vo.ParamDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MqProcessor implements Processor {
    @Produce
    protected ProducerTemplate template;

    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        String s = new String((byte[]) in.getBody(), "UTF-8");
        ParamDto paramDto = JSON.parseObject(s, ParamDto.class);
        in.setBody(paramDto, ParamDto.class);
    }
}
