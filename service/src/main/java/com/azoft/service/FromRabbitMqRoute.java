package com.azoft.service;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FromRabbitMqRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("rabbitmq://localhost:5672/MyExchange?queue=MyQueue&autoDelete=false&durable=true")
                .convertBodyTo(String.class)
                .process(exchange ->
                        System.out.println(exchange.getIn().getHeader("Type")  + "" +exchange.getIn().getBody())).end();
    }
}