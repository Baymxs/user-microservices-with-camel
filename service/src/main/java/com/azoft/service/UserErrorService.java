package com.azoft.service;

import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class UserErrorService {
    public void typeUnknownError(Exchange exchange) {
        exchange.getIn().setBody("Request type is unknown");
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "text/plain");
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
    }

}
