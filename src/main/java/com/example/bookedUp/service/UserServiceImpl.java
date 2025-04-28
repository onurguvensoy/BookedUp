package com.example.bookedUp.service;

import com.example.bookedUp.dto.UserDto;
import com.example.bookedUp.facade.UserFacade;
import com.example.bookedUp.model.User;
import com.example.bookedUp.model.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserFacade userFacade;

    public UserServiceImpl(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Override
    @Transactional
    public UserDto createUser(String email, String password, String firstName, String lastName, Role role) {
        User user = userFacade.createUser(email, password, firstName, lastName, role);
        return convertToDto(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        return userFacade.getUserById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userFacade.getAllUsers().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersByRole(Role role) {
        return userFacade.getUsersByRole(role).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = convertToEntity(userDto);
        User updatedUser = userFacade.updateUser(id, user);
        return convertToDto(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userFacade.deleteUser(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userFacade.existsByEmail(email);
    }

    private UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.isEnabled()
        );
    }

    private User convertToEntity(UserDto userDto) {
        User user = userFacade.createUser(
                userDto.getEmail(),
                "", // Password is not included in DTO
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getRole()
        );
        user.setId(userDto.getId());
        user.setEnabled(userDto.isEnabled());
        return user;
    }
} 