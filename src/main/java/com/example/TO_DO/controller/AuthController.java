package com.example.TO_DO.controller;

import com.example.TO_DO.dto.AuthResponse;
import com.example.TO_DO.dto.LoginRequest;
import com.example.TO_DO.dto.RegisterRequest;
import com.example.TO_DO.model.User;
import com.example.TO_DO.repository.UserRepository;
import com.example.TO_DO.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        String token = authService.register(request.getName(),
                                            request.getEmail(),
                                            request.getPassword());

        User user = userRepository.findByEmail(request.getEmail()).get();

        return ResponseEntity.ok(new AuthResponse(token, user.getName(), user.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.getEmail(),
                                         request.getPassword());

        User user = userRepository.findByEmail(request.getEmail()).get();

        return ResponseEntity.ok(new AuthResponse(token, user.getName(), user.getEmail()));
    }
}