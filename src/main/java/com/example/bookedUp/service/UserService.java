package com.example.bookedUp.service;

import com.example.bookedUp.dto.UserDto;
import com.example.bookedUp.model.Role;
import java.util.List;
import java.util.Set;

public interface UserService {
    UserDto createUser(String email, String password, String firstName, String lastName, Set<Role.RoleType> roleTypes);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    List<UserDto> getUsersByRole(Role.RoleType roleType);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    boolean existsByEmail(String email);
} 