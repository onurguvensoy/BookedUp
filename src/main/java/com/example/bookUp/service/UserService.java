package com.example.bookUp.service;

import com.example.bookUp.dto.UserDto;
import com.example.bookUp.model.Role;
import java.util.List;

public interface UserService {
    UserDto createUser(String email, String password, String firstName, String lastName, Role role);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    List<UserDto> getUsersByRole(Role role);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    boolean existsByEmail(String email);
} 