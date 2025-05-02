package com.example.bookedUp.service;

import com.example.bookedUp.dto.LoginRequest;
import com.example.bookedUp.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
    boolean validateToken(String token);
    Long getUserIdFromToken(String token);
} 