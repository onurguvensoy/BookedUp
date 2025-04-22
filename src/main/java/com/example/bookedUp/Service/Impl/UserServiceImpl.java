package com.example.bookedUp.Service.Impl;

import com.example.bookedUp.Factory.UserFactory;
import com.example.bookedUp.Model.User;
import com.example.bookedUp.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User createUser(String type, String username, String email) {
        User user = UserFactory.createUser(type);
        user.setUsername(username);
        user.setEmail(email);
        return user;
    }
}