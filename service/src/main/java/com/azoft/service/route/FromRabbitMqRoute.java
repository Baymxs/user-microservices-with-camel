package com.azoft.service.route;

import com.azoft.apimodel.creation.UserCreationReq;
import com.azoft.apimodel.updating.UserUpdatingReq;
import com.azoft.service.UserErrorService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@Component
public class FromRabbitMqRoute extends RouteBuilder {
    @Override
    public void configure() {
        from("rabbitmq://localhost:5672/MyExchange?queue=MyQueue&autoDelete=false&durable=true")
                .choice()
                    .when(header("rabbitmq.TYPE").isEqualTo("create"))
                        .log("User creation request")
                        .unmarshal().json(JsonLibrary.Jackson, UserCreationReq.class)
                        .process("createReqProcessor")
                    .when(header("rabbitmq.TYPE").isEqualTo("update"))
                        .log("User updating request")
                        .unmarshal().json(JsonLibrary.Jackson, UserUpdatingReq.class)
                        .process("updateReqProcessor")
                    .when(header("rabbitmq.TYPE").isEqualTo("get"))
                        .log("User getting request")
                        .process("getReqProcessor")
                    .when(header("rabbitmq.TYPE").isEqualTo("delete"))
                        .log("User deleting request")
                        .process("updateReqProcessor")
                    .otherwise()
                        .bean(new UserErrorService(), "typeUnknownError")
                .end();
    }
}