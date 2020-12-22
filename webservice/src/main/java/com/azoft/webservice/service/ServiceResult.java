package com.azoft.webservice.service;

import lombok.Getter;

@Getter
public class ServiceResult<T> {
    private T body;
    private ServiceStatus status;
}
