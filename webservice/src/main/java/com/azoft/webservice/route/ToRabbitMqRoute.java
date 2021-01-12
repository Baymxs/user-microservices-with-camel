package com.azoft.webservice.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.rabbitmq.RabbitMQConstants;
import org.springframework.stereotype.Component;

@Component
public class ToRabbitMqRoute extends RouteBuilder {
    @Override
    public void configure() {
        restConfiguration().component("servlet");

        rest("users").description("User rest service")
                .consumes("application/json").produces("application/json")
                .post().description("Create user")
                .route().setHeader(RabbitMQConstants.TYPE, constant("create"))
                .log("User creation request received")
                .to("rabbitmq://localhost:5672/MyExchange?queue=MyQueue&autoDelete=false&durable=true").endRest()

                .put("/{id}").description("Update user")
                .route().setHeader(RabbitMQConstants.TYPE, constant("update"))
                .log("User updating request received")
                .to("rabbitmq://localhost:5672/MyExchange?queue=MyQueue&autoDelete=false&durable=true").endRest()

                .get("/{id}").description("Get user")
                .route().setHeader(RabbitMQConstants.TYPE, constant("get"))
                .log("User getting request received")
                .to("rabbitmq://localhost:5672/MyExchange?queue=MyQueue&autoDelete=false&durable=true").endRest()

                .delete("/{id}").description("Delete user")
                .route().setHeader(RabbitMQConstants.TYPE, constant("delete"))
                .log("User deleting request received")
                .to("rabbitmq://localhost:5672/MyExchange?queue=MyQueue&autoDelete=false&durable=true");
    }
}