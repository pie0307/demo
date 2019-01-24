package com.amy.pie.camel.route;

import com.amy.pie.camel.processor.HttpProcessor;
import com.amy.pie.camel.processor.MqProcessor;
import com.amy.pie.camel.vo.ParamDto;
import org.apache.camel.Exchange;
import org.apache.camel.model.dataformat.JsonLibrary;
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

//        from("rabbitmq://sms.mq.t.com:5672/t.EX_ehrnew_empInfo_psJob?" +
//                "username=phoenix_rabbit_write" +
//                "&password=phoenix_rabbit_write" +
//                "&routingKey=ehrnew.empInfo.psJob" +
//                "&vhost=phoenix&exchangeType=topic&autoDelete=false&durable=true&queue=liuby_camel_test")
//                .setHeader(Exchange.HTTP_METHOD, constant("POST")).convertBodyTo(ParamDto.class)
//                .multicast().to("http4://localhost:8082/myapp/myservice", "http4://localhost:8083/myapp/myservice")
//                .to("http4://localhost:8084/myapp/myservice").process(new HttpProcessor());

        from("rabbitmq://sms.mq.t.com:5672/t.EX_ehrnew_empInfo_psJob?" +
                "username=phoenix_rabbit_write" +
                "&password=phoenix_rabbit_write" +
                "&routingKey=ehrnew.empInfo.psJob" +
                "&vhost=phoenix&exchangeType=topic&autoDelete=false&durable=true&queue=liuby_camel_test")
                .convertBodyTo(String.class, "UTF-8")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .multicast()
                .to("http4://localhost:8082/myapp/myservice").process(new HttpProcessor())
                .to("http4://localhost:8083/myapp/myservice").process(new HttpProcessor())
                .to("http4://localhost:8084/myapp/myservice").process(new HttpProcessor());

    }
}
