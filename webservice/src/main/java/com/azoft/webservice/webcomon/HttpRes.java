package com.azoft.webservice.webcomon;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpRes {
    static public <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok(body);
    }

    static public <T> ResponseEntity<T> ok() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(null);
    }

    static public ResponseEntity<String> conflict(HttpStatus httpStatus, String message) {
        return ResponseEntity
                .status(httpStatus)
                .body(message);
    }
}
