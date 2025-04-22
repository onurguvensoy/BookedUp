package com.example.bookedUp.Model;

import jakarta.persistence.Entity;

@Entity
public class Admin extends User {

    @Override
    public String getRole() {
        return "Admin";
    }
}
