package com.example.bookedUp.factory;

import com.example.bookedUp.model.*;
import org.springframework.stereotype.Component;

@Component
public class UserFactoryImpl implements UserFactory {
    @Override
    public User createUser(String email, String password, String firstName, String lastName, Role role) {
        User user;
        switch (role) {
            case GUEST:
                user = Guest.builder()
                        .email(email)
                        .password(password)
                        .firstName(firstName)
                        .lastName(lastName)
                        .role(role)
                        .enabled(true)
                        .build();
                break;
            case HOST:
                user = Host.builder()
                        .email(email)
                        .password(password)
                        .firstName(firstName)
                        .lastName(lastName)
                        .role(role)
                        .enabled(true)
                        .build();
                break;
            case ADMIN:
                user = Admin.builder()
                        .email(email)
                        .password(password)
                        .firstName(firstName)
                        .lastName(lastName)
                        .role(role)
                        .enabled(true)
                        .build();
                break;
            default:
                throw new IllegalArgumentException("Invalid role: " + role);
        }
        return user;
    }
} 