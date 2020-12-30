package com.azoft.webservice.service;

import lombok.Getter;

@Getter
public class ServiceRes<T> {
    private T body;
    private ServiceStatus status;
}
