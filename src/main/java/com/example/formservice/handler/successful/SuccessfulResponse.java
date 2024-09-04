package com.example.formservice.handler.successful;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessfulResponse<T> {
    private LocalDateTime timestamp;
    private String message;
    private T data;
    private int status;

    public SuccessfulResponse(String message, T data, int status) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.data = data;
        this.status = status;
    }
}