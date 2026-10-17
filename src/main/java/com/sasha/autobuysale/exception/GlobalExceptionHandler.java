package com.sasha.autobuysale.exception;

import com.sasha.autobuysale.dto.ResponseExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CarExists.class)
    public ResponseEntity<ResponseExceptionDTO> carExists(CarExists e) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ResponseExceptionDTO(e.getMessage(), HttpStatus.CONFLICT.value()));
    }
}
