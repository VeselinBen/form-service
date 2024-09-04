package com.example.formservice.handler.successful;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class SuccessResponseHandler {

    public <T> ResponseEntity<SuccessfulResponse<T>> handleSuccess(T data, String message, HttpStatus status) {
        SuccessfulResponse<T> response = new SuccessfulResponse<>(message, data, status.value());
        return new ResponseEntity<>(response, status);
    }

    public <T> ResponseEntity<SuccessfulResponse<T>> ok(T data) {
        return handleSuccess(data, "Operation completed successfully", HttpStatus.OK);
    }

    public <T> ResponseEntity<SuccessfulResponse<T>> created(T data) {
        return handleSuccess(data, "Resource created successfully", HttpStatus.CREATED);
    }

    public <T> ResponseEntity<SuccessfulResponse<T>> accepted(T data) {
        return handleSuccess(data, "Request accepted successfully", HttpStatus.ACCEPTED);
    }


    public <T> ResponseEntity<SuccessfulResponse<T>> unauthorized(T data) {
        return handleSuccess(data, "Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    public <T> ResponseEntity<SuccessfulResponse<T>> noContent() {
        return handleSuccess(null, "No content", HttpStatus.NO_CONTENT);
    }
}
