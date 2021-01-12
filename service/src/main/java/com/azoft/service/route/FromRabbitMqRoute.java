package com.azoft.service.route;

import com.azoft.apimodel.creation.UserCreationReq;
import com.azoft.apimodel.updating.UserUpdatingReq;
import com.azoft.service.error.UserErrorService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class FromRabbitMqRoute extends RouteBuilder {
    @Override
    public void configure() {
        onException(Throwable.class)
                .handled(true)
                .bean(new UserErrorService(), "handleException")
                .marshal().json();

        from("rabbitmq://localhost:5672/MyExchange?queue=MyQueue&autoDelete=false&durable=true")
                .choice()
                    .when(header("rabbitmq.TYPE").isEqualTo("create"))
                        .log("User creation request received")
                        .unmarshal().json(JsonLibrary.Jackson, UserCreationReq.class)
                        .process("createReqProcessor")
                        .marshal().json().endChoice()
                    .when(header("rabbitmq.TYPE").isEqualTo("update"))
                        .log("User updating request received")
                        .unmarshal().json(JsonLibrary.Jackson, UserUpdatingReq.class)
                        .process("updateReqProcessor")
                        .marshal().json().endChoice()
                .when(header("rabbitmq.TYPE").isEqualTo("get"))
                        .log("User getting request received")
                        .process("getReqProcessor")
                        .marshal().json().endChoice()
                .when(header("rabbitmq.TYPE").isEqualTo("delete"))
                        .log("User deleting request received")
                        .process("deleteReqProcessor")
                        .marshal().json().endChoice()
                .otherwise()
                        .bean(new UserErrorService(), "handleTypeUnknownError")
                        .marshal().json()
                .end();
    }
}