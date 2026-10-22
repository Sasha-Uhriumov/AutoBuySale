package com.sasha.autobuysale.exception;

import com.sasha.autobuysale.dto.ResponseExceptionDTO;
import com.sasha.autobuysale.dto.ResponseValidationExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ResponseValidationExceptionDTO>> validationException(MethodArgumentNotValidException e) {

        List<ResponseValidationExceptionDTO> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new ResponseValidationExceptionDTO(
                        err.getField(),
                        err.getDefaultMessage()
                )).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CarExists.class)
    public ResponseEntity<ResponseExceptionDTO> carExists(CarExists e) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ResponseExceptionDTO(e.getMessage(), HttpStatus.CONFLICT.value()));
    }

    @ExceptionHandler(CarNotFound.class)
    public ResponseEntity<ResponseExceptionDTO> carNotFound(CarNotFound e) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ResponseExceptionDTO(e.getMessage(), HttpStatus.NOT_FOUND.value()));
    }
}
