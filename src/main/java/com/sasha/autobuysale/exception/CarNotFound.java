package com.sasha.autobuysale.exception;

public class CarNotFound extends RuntimeException {
    public CarNotFound(String message) {
        super(message);
    }
}
