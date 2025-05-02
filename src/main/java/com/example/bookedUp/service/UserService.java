package com.example.bookedUp.service;

import com.example.bookedUp.dto.UserDto;
import com.example.bookedUp.dto.UserUpdateRequest;
import com.example.bookedUp.model.Role;
import com.example.bookedUp.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    UserDto createUser(User user);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    List<UserDto> getUsersByRole(Role.RoleType roleType);
    UserDto updateUser(Long id, UserUpdateRequest request);
    void deleteUser(Long id);
    boolean existsByEmail(String email);
} 