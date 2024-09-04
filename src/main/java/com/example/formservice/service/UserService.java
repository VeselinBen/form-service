package com.example.formservice.service;

import com.example.formservice.dto.ForgotPasswordRequestDto;
import com.example.formservice.dto.RegisterUserDto;
import com.example.formservice.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User createUser(User user);
    User getUserById(UUID id);
    List<User> getAllUsers();
    User updateUser(UUID id, User user);
    void deleteUser(UUID id);
    User createUser(RegisterUserDto registerUserDto);
    User getUserByEmail(String email);
    void sendForgotPasswordResetEmail(ForgotPasswordRequestDto request);
    void resetPassword(String token, String newPassword);
}
