package com.example.bookedUp.service;

import com.example.bookedUp.dto.LoginRequest;
import com.example.bookedUp.dto.LoginResponse;
import com.example.bookedUp.dto.UserCreateRequest;
import com.example.bookedUp.dto.UserDto;

public interface AuthService {
    UserDto register(UserCreateRequest request);
    LoginResponse login(LoginRequest request);
    boolean validateToken(String token);
    Long getUserIdFromToken(String token);
} 