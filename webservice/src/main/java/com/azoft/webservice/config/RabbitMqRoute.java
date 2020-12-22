package com.azoft.webservice.config;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("direct:startRabbitMQPoint").
                process("headerProcessor").
                marshal().
                json(JsonLibrary.Jackson).
                to("rabbitmq://localhost:5672/MyExchange?queue=MyQueue&autoDelete=false&durable=true").end();
    }
}