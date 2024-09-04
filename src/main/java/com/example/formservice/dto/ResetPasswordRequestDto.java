package com.example.formservice.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResetPasswordRequestDto {
    @NotBlank
    private String token;

    @NotBlank
    private String newPassword;
}