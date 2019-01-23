package com.amy.pie.camel;

import org.apache.camel.spring.boot.CamelSpringBootApplicationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Author : liuby.
 * Description :
 * Date : Created in 2019/1/23 15:43
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        final ApplicationContext context = new SpringApplication(Application.class).run(args);
        final CamelSpringBootApplicationController controller = context.getBean(CamelSpringBootApplicationController.class);
        controller.run();
    }
}
