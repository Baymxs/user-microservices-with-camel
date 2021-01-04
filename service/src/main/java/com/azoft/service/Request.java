package com.azoft.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Request<T> {
    private final String type;
    private final T data;
    private final String replyTo;
}
