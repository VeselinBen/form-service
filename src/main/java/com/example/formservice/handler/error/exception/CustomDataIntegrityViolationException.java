package com.example.formservice.handler.error.exception;

public class CustomDataIntegrityViolationException extends RuntimeException {
    public CustomDataIntegrityViolationException(String message) {
        super(message);
    }
}
