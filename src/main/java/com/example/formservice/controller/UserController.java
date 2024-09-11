package com.example.formservice.controller;

import com.example.formservice.auth.JwtService;
import com.example.formservice.dto.AuthUserDto;
import com.example.formservice.dto.ForgotPasswordRequestDto;
import com.example.formservice.dto.RegisterUserDto;
import com.example.formservice.dto.ResetPasswordRequestDto;
import com.example.formservice.handler.successful.SuccessResponseHandler;
import com.example.formservice.handler.successful.SuccessfulResponse;
import com.example.formservice.model.User;
import com.example.formservice.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/forms/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final SuccessResponseHandler successResponseHandler;

    @GetMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<User>> getUserById(@PathVariable UUID id) {
        return successResponseHandler.created(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<SuccessfulResponse<List<User>>> getAllUsers() {
        return successResponseHandler.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<User>> updateUser(@PathVariable UUID id, @RequestBody User user) {
        return successResponseHandler.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessfulResponse<Object>> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return successResponseHandler.noContent();
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessfulResponse<User>> registerUser(@Valid @RequestBody RegisterUserDto registerUserDto) {
        return successResponseHandler.created(userService.createUser(registerUserDto));
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessfulResponse<String>> loginUser(@RequestBody @Valid AuthUserDto loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtService.generateToken(userDetails);

        return successResponseHandler.ok(jwt);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<SuccessfulResponse<String>> forgotPassword(@RequestBody @Valid ForgotPasswordRequestDto forgotPasswordRequestDto) {
        userService.sendForgotPasswordResetEmail(forgotPasswordRequestDto);
        return successResponseHandler.ok("If the email is registered, a password reset link has been sent.");
    }


    @PostMapping("/reset-password")
    public ResponseEntity<SuccessfulResponse<String>> resetPassword(@RequestBody @Valid ResetPasswordRequestDto resetPasswordRequest) {
        userService.resetPassword(resetPasswordRequest.getToken(), resetPasswordRequest.getNewPassword());
        return successResponseHandler.ok("Password has been reset successfully.");
    }
}
