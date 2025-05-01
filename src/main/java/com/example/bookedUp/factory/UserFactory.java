package com.example.bookedUp.factory;

import com.example.bookedUp.model.User;
import com.example.bookedUp.model.Role;
import java.util.Set;

public interface UserFactory {
    User createUser(String email, String password, String firstName, String lastName, Set<Role> roles);
} 