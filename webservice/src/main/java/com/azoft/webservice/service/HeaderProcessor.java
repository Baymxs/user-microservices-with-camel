package com.azoft.webservice.service;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.rabbitmq.RabbitMQConstants;
import org.springframework.stereotype.Component;

@Component
public class HeaderProcessor implements Processor {
    @Override
    public void process(Exchange exchange) {
        exchange.getIn().setHeader(RabbitMQConstants.TYPE, UserService.getMessageType());
    }
}
