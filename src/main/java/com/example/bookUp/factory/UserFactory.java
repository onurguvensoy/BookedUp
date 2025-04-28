package com.example.bookUp.factory;

import com.example.bookUp.model.User;
import com.example.bookUp.model.Role;

public interface UserFactory {
    User createUser(String email, String password, String firstName, String lastName, Role role);
} 