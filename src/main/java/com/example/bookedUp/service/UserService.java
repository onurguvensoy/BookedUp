package com.example.bookedUp.service;

import com.example.bookedUp.dto.UserDto;
import com.example.bookedUp.model.Role;
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