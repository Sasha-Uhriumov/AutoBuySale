package com.sasha.autobuysale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseValidationExceptionDTO {

    private final String status;
    private final String message;
}
