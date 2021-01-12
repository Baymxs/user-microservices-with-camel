package com.azoft.service.webcommon;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.camel.Exchange;
import org.apache.http.HttpStatus;

public class Http {
    public static void success(Exchange exchange) {
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.SC_OK);
    }

    public static void success(Exchange exchange, Object body) {
        exchange.getIn().setBody(null);
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.SC_OK);
    }

    public static void badRequest(Exchange exchange, String messageBody) {
        exchange.getIn().setBody(new Message(messageBody));
        exchange.getIn().setHeader(Exchange.CONTENT_TYPE, "application/json");
        exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.SC_BAD_REQUEST);
    }

    @RequiredArgsConstructor
    @Getter
    private static class Message {
        private final String message;
    }
}
