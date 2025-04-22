package com.example.bookedUp.Model;

import jakarta.persistence.Entity;

@Entity
public class Host extends User {

    @Override
    public String getRole() {
        return "Host";
    }
}
