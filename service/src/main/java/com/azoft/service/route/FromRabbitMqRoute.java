package com.azoft.service.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class FromRabbitMqRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("rabbitmq://localhost:5672/MyExchange?queue=MyQueue&autoDelete=false&durable=true")
                .process("jsonProcessor")
                .process("requestProcessor");
    }
}