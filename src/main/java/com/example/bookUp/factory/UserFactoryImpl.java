package com.example.bookUp.factory;

import com.example.bookUp.model.User;
import com.example.bookUp.model.Role;
import org.springframework.stereotype.Component;

@Component
public class UserFactoryImpl implements UserFactory {
    @Override
    public User createUser(String email, String password, String firstName, String lastName, Role role) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        user.setEnabled(true);
        return user;
    }
} 