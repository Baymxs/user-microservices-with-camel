package com.azoft.webservice.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class ToRabbitMqRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("direct:rabbitMQPoint").
                process("headerProcessor").
                marshal().
                json(JsonLibrary.Jackson).
                to("rabbitmq://localhost:5672/MyExchange?queue=MyQueue&autoDelete=false&durable=true").end();
    }
}