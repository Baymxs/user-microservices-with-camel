package com.azoft.webservice.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Req {
    private final ReqType type;
    private final Object data;
}
