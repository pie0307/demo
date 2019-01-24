package com.amy.pie.camel.processor;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/23 15:47
 */

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@Slf4j
public class HttpProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Message in = exchange.getIn();
        String body = in.getBody(String.class);
        log.info("http receive message:{}", body);

        String body1 = exchange.getOut().getBody(String.class);
        Map<String, Object> headers = exchange.getOut().getHeaders();
        log.info("http receive message:{}", body1);
//        HttpServletRequest request = in.getBody(HttpServletRequest.class);
//        HttpServletResponse response = in.getBody(HttpServletResponse.class);
//        ServletOutputStream outputStream = response.getOutputStream();
//        log.info("http receive message:{}", outputStream.toString());
    }
}
