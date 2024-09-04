package com.example.formservice.service.impl;

import com.example.formservice.auth.JwtService;
import com.example.formservice.dto.ForgotPasswordRequestDto;
import com.example.formservice.dto.RegisterUserDto;
import com.example.formservice.enums.UserRole;
import com.example.formservice.handler.error.exception.UnauthorizedException;
import com.example.formservice.handler.error.exception.UserNotFoundException;
import com.example.formservice.model.User;
import com.example.formservice.repository.UserRepository;
import com.example.formservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;
    private final JwtService jwtService;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(UUID id, User updatedUser) {
        User user = getUserById(id);
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        user.setRole(updatedUser.getRole());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public User createUser(RegisterUserDto registerUserDto) {
        User user = User.builder()
                .username(registerUserDto.getUsername())
                .email(registerUserDto.getEmail())
                .password(passwordEncoder.encode(registerUserDto.getPassword()))
                .role(UserRole.USER_ROLE)
                .build();
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found"));
    }

    @Override
    public void sendForgotPasswordResetEmail(ForgotPasswordRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + request.getEmail()));

        // Generate password reset token
        String resetToken = jwtService.generateForgotPasswordResetToken(user);

        // Create the request body for the email service
        String emailServiceUrl = "http://localhost:8082/emails/send";  // Adjust the URL as needed
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("to", user.getEmail());
        requestBody.put("subject", "Password Reset Request");
        requestBody.put("text", "To reset your password, please click the following link: " +
                "http://your-frontend-url/reset-password?token=" + resetToken);

        // Send the email using the email microservice
        restTemplate.postForObject(emailServiceUrl, requestBody, Void.class);
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        // Extract the username from the token
        String username = jwtService.extractUsername(token);

        // Load the user details using the extracted username
        UserDetails userDetails = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        // Validate the token
        if (!jwtService.isPasswordResetTokenValid(token, userDetails)) {
            throw new UnauthorizedException("Invalid or expired token for reset password.");
        }

        // Update the password
        User user = (User) userDetails;
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
