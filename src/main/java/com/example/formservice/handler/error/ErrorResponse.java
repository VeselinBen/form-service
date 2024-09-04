package com.example.formservice.handler.error;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class ErrorResponse {

    private final String series;
    private final int status;
    private final String message;
    private final String path;
    private final LocalDateTime timestamp;
    private String exceptionMessage;
    @Setter
    private Map<String, String> validationErrors;

    public ErrorResponse(HttpStatus status, String message, String path, String exceptionMessage) {
        this.series = status.series().name();
        this.status = status.value();
        this.message = message;
        this.path = path;
        this.exceptionMessage = exceptionMessage;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(HttpStatus status, String message, String path) {
        this.series = status.series().name();
        this.status = status.value();
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}
