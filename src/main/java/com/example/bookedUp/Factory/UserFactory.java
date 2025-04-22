package com.example.bookedUp.Factory;

import com.example.bookedUp.Model.User;
import com.example.bookedUp.Model.Guest;
import com.example.bookedUp.Model.Host;
import com.example.bookedUp.Model.Admin;

public class UserFactory {
    public static User createUser(String type) {
        return switch (type.toLowerCase()) {
            case "guest" -> new Guest();
            case "host" -> new Host();
            case "admin" -> new Admin();
            default -> throw new IllegalArgumentException("Invalid user type: " + type);
        };
    }
}