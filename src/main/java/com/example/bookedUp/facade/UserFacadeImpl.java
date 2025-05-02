package com.example.bookedUp.facade;

import com.example.bookedUp.dto.UserDto;
import com.example.bookedUp.dto.UserCreateRequest;
import com.example.bookedUp.dto.UserUpdateRequest;
import com.example.bookedUp.model.Role;
import com.example.bookedUp.model.User;
import com.example.bookedUp.service.RoleService;
import com.example.bookedUp.service.UserService;
import com.example.bookedUp.factory.UserFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;
    private final RoleService roleService;
    private final UserFactory userFactory;

    public UserFacadeImpl(UserService userService, RoleService roleService, UserFactory userFactory) {
        this.userService = userService;
        this.roleService = roleService;
        this.userFactory = userFactory;
    }

    @Override
    @Transactional
    public UserDto createUser(UserCreateRequest request) {
        if (userService.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Create roles first
        Set<Role> roles = roleService.getRolesByTypes(request.getRoles());
        
        // Create the appropriate user type using the factory
        User user = userFactory.createUser(
            request.getEmail(),
            request.getPassword(),
            request.getFirstName(),
            request.getLastName(),
            roles
        );
        
        // Save the user
        UserDto savedUser = userService.createUser(user);
        
        // Verify the user was saved
        if (savedUser == null) {
            throw new RuntimeException("Failed to save user");
        }
        
        return savedUser;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        return userService.getUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getUsersByRole(Role.RoleType roleType) {
        return userService.getUsersByRole(roleType);
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserUpdateRequest request) {
        if (request.getRoles() != null) {
            Set<Role> roles = roleService.getRolesByTypes(request.getRoles());
            request.setRoles(roles.stream().map(Role::getName).collect(Collectors.toSet()));
        }
        return userService.updateUser(id, request);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userService.existsByEmail(email);
    }
} 