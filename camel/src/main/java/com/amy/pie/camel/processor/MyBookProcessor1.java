package com.amy.pie.camel.processor;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/23 15:47
 */

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class MyBookProcessor1 implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getOut().setBody("<html><body>Book is good1</body></html>");
    }
}
