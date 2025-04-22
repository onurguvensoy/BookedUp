package com.example.bookedUp.Service;

import com.example.bookedUp.Model.User;

public interface UserService {
    User createUser(String type, String name, String email);
}