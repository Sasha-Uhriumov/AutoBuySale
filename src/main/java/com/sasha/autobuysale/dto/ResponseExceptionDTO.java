package com.sasha.autobuysale.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseExceptionDTO {

    private final String message;
    private final Integer status;
}
