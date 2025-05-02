package com.example.bookedUp.controller;

import com.example.bookedUp.dto.LoginRequest;
import com.example.bookedUp.dto.LoginResponse;
import com.example.bookedUp.dto.UserCreateRequest;
import com.example.bookedUp.dto.UserDto;
import com.example.bookedUp.facade.UserFacade;
import com.example.bookedUp.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserFacade userFacade;

    public AuthController(AuthService authService, UserFacade userFacade) {
        this.authService = authService;
        this.userFacade = userFacade;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserCreateRequest request) {
        UserDto userDto = userFacade.createUser(request);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
} 