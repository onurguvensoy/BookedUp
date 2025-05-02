package com.example.bookedUp.service;

import com.example.bookedUp.dto.UserDto;
import com.example.bookedUp.dto.UserUpdateRequest;
import com.example.bookedUp.model.Role;
import com.example.bookedUp.model.User;
import com.example.bookedUp.model.Host;
import com.example.bookedUp.model.Guest;
import com.example.bookedUp.model.Admin;
import com.example.bookedUp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDto createUser(User user) {
        // Save the user, which will automatically save the specific type (Host, Guest, Admin)
        User savedUser = userRepository.save(user);
        
        // Verify the user was saved with the correct type
        if (user instanceof Host) {
            Host savedHost = (Host) savedUser;
            if (savedHost.getId() == null) {
                throw new RuntimeException("Failed to save host user");
            }
        } else if (user instanceof Guest) {
            Guest savedGuest = (Guest) savedUser;
            if (savedGuest.getId() == null) {
                throw new RuntimeException("Failed to save guest user");
            }
        } else if (user instanceof Admin) {
            Admin savedAdmin = (Admin) savedUser;
            if (savedAdmin.getId() == null) {
                throw new RuntimeException("Failed to save admin user");
            }
        }
        
        return convertToDto(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        return convertToDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getUsersByRole(Role.RoleType roleType) {
        return userRepository.findByRoles_Name(roleType).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));

        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getFirstName() != null) {
            user.setFirstName(request.getFirstName());
        }
        if (request.getLastName() != null) {
            user.setLastName(request.getLastName());
        }

        User updatedUser = userRepository.save(user);
        return convertToDto(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setRoles(user.getRoles());
        dto.setEnabled(user.isEnabled());
        return dto;
    }
} 