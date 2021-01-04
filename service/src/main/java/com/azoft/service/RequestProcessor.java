package com.azoft.service;

import com.azoft.apimodel.creation.UserCreationReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.rabbitmq.RabbitMQConstants;
import org.springframework.stereotype.Component;

@Component
public class RequestProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        switch (exchange.getIn().getHeader(RabbitMQConstants.TYPE).toString()) {
            case "create":
                UserCreationReq userCreationReq = (UserCreationReq) convertJsonToObject(exchange.getIn().getBody().toString());
                System.out.println(userCreationReq.getName());
                break;
            case "update":
                System.out.println();
                break;
            case "get":
                System.out.println();
                break;
            case "delete":
                System.out.println();
                break;
        }
    }

    public Object convertJsonToObject(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, Object.class);
    }
}
