package com.example.bookedUp.service;

import com.example.bookedUp.dto.LoginRequest;
import com.example.bookedUp.dto.LoginResponse;
import com.example.bookedUp.dto.UserCreateRequest;
import com.example.bookedUp.dto.UserDto;
import com.example.bookedUp.model.User;
import com.example.bookedUp.model.Role;
import com.example.bookedUp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final UserRepository userRepository;

    public AuthServiceImpl(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDto register(UserCreateRequest request) {
        return userService.createUser(
            request.getEmail(),
            request.getPassword(),
            request.getFirstName(),
            request.getLastName(),
            request.getRoles()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String token = generateToken(user);

        return new LoginResponse(
            user.getId(),
            user.getEmail(),
            user.getFirstName(),
            user.getLastName(),
            user.getRoles(),
            token
        );
    }

    @Override
    public boolean validateToken(String token) {
        try {
            String decodedToken = new String(Base64.getDecoder().decode(token));
            String[] parts = decodedToken.split("\\|");
            if (parts.length != 3) return false;

            Long userId = Long.parseLong(parts[0]);
            String email = parts[1];
            long expiryTime = Long.parseLong(parts[2]);

            if (expiryTime < System.currentTimeMillis()) return false;

            return userRepository.existsByIdAndEmail(userId, email);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Long getUserIdFromToken(String token) {
        try {
            String decodedToken = new String(Base64.getDecoder().decode(token));
            String[] parts = decodedToken.split("\\|");
            if (parts.length != 3) return null;
            return Long.parseLong(parts[0]);
        } catch (Exception e) {
            return null;
        }
    }

    private String generateToken(User user) {
        long expiryTime = System.currentTimeMillis() + (24 * 60 * 60 * 1000); // 24 hours
        String tokenData = user.getId() + "|" + user.getEmail() + "|" + expiryTime;
        return Base64.getEncoder().encodeToString(tokenData.getBytes());
    }
} 