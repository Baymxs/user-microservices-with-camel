package com.azoft.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class JsonProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        String jsonBody = exchange.getIn().getBody(String.class);
        exchange.getIn().setBody(convertJsonToObject(jsonBody));
    }

    public Object convertJsonToObject(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, Object.class);
    }
}
