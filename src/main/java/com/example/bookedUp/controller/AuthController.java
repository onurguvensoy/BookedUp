package com.example.bookedUp.controller;

import com.example.bookedUp.dto.LoginRequest;
import com.example.bookedUp.dto.LoginResponse;
import com.example.bookedUp.dto.UserCreateRequest;
import com.example.bookedUp.dto.UserDto;
import com.example.bookedUp.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserCreateRequest request) {
        UserDto userDto = authService.register(request);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
} 