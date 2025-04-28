package com.example.bookedUp.factory;

import com.example.bookedUp.model.User;
import com.example.bookedUp.model.Role;

public interface UserFactory {
    User createUser(String email, String password, String firstName, String lastName, Role role);
} 