package com.example.bookedUp.facade;

import com.example.bookedUp.dto.UserDto;
import com.example.bookedUp.dto.UserCreateRequest;
import com.example.bookedUp.dto.UserUpdateRequest;
import com.example.bookedUp.model.Role;
import java.util.List;

public interface UserFacade {
    UserDto createUser(UserCreateRequest request);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    List<UserDto> getUsersByRole(Role.RoleType roleType);
    UserDto updateUser(Long id, UserUpdateRequest request);
    void deleteUser(Long id);
    boolean existsByEmail(String email);
} 