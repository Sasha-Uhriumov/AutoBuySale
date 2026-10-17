package com.sasha.autobuysale.exception;

public class CarExists extends RuntimeException {
    public CarExists(String message) {
        super(message);
    }
}
