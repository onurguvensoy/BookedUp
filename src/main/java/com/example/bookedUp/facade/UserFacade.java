package com.example.bookedUp.facade;

import com.example.bookedUp.model.User;
import com.example.bookedUp.model.Role;
import java.util.List;
import java.util.Set;
import java.util.Optional;

public interface UserFacade {
    User createUser(String email, String password, String firstName, String lastName, Set<Role> roles);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    List<User> getUsersByRole(Role.RoleType roleType);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
    boolean existsByEmail(String email);
} 