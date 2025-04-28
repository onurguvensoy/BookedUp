package com.example.bookUp.facade;

import com.example.bookUp.model.User;
import com.example.bookUp.model.Role;
import java.util.List;
import java.util.Optional;

public interface UserFacade {
    User createUser(String email, String password, String firstName, String lastName, Role role);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    List<User> getUsersByRole(Role role);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    boolean existsByEmail(String email);
} 