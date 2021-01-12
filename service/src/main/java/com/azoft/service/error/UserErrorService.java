package com.azoft.service.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class UserErrorService {
    public void handleTypeUnknownError(Exchange exchange) {
        String type = exchange.getIn().getHeader("rabbitmq.TYPE").toString();

        exchange.getIn().setBody(new ErrorMessage("Request type " + type + " is unknown"));
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 405);
    }

    public void handleException(Exchange exchange) {
        Throwable ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);

        exchange.getIn().setBody(new ErrorMessage(ex.getMessage()));
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
    }

    @RequiredArgsConstructor
    @Getter
    private static class ErrorMessage {
        private final String message;
    }
}
