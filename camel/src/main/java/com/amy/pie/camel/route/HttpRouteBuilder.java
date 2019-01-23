package com.amy.pie.camel.route;

import com.amy.pie.camel.processor.MyBookProcessor;
import com.amy.pie.camel.processor.MyBookProcessor1;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/23 15:46
 */
@Component
public class HttpRouteBuilder extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {
        from("jetty:http://localhost:8080/myapp/myservice")
                .process(new MyBookProcessor())
                .process(new MyBookProcessor1())
                .to("jetty:http://localhost:8082/myapp/myservice");
    }
}
