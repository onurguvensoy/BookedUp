package com.example.bookedUp.factory;

import com.example.bookedUp.model.*;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserFactoryImpl implements UserFactory {
    @Override
    public User createUser(String email, String password, String firstName, String lastName, Set<Role> roles) {
        User user = User.builder()
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .roles(roles)
                .enabled(true)
                .build();

        // Add specific user type based on roles
        if (roles.stream().anyMatch(role -> role.getName() == Role.RoleType.HOST)) {
            return Host.builder()
                    .email(email)
                    .password(password)
                    .firstName(firstName)
                    .lastName(lastName)
                    .roles(roles)
                    .enabled(true)
                    .build();
        } else if (roles.stream().anyMatch(role -> role.getName() == Role.RoleType.GUEST)) {
            return Guest.builder()
                    .email(email)
                    .password(password)
                    .firstName(firstName)
                    .lastName(lastName)
                    .roles(roles)
                    .enabled(true)
                    .build();
        } else if (roles.stream().anyMatch(role -> role.getName() == Role.RoleType.ADMIN)) {
            return Admin.builder()
                    .email(email)
                    .password(password)
                    .firstName(firstName)
                    .lastName(lastName)
                    .roles(roles)
                    .enabled(true)
                    .build();
        }

        return user;
    }
} 