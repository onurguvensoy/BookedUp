package com.example.bookedUp.Model;

import jakarta.persistence.Entity;

@Entity
public class Guest extends User {

    // Implement the abstract method from User
    @Override
    public void someAbstractMethod() {
        // Provide the implementation specific to Guest
    }

    // You can add other fields and methods specific to Guest if necessary
}
