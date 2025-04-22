package com.example.bookedUp.Factory;

import com.example.bookedUp.Model.Rental;
import com.example.bookedUp.Model.Villa;
import com.example.bookedUp.Model.Apartment;
import com.example.bookedUp.Model.Hotel;

public class RentalFactory {
    public static Rental createRental(String type) {
        return switch (type.toLowerCase()) {
            case "villa" -> new Villa();
            case "apartment" -> new Apartment();
            case "hotel" -> new Hotel();
            default -> throw new IllegalArgumentException("Invalid rental type: " + type);
        };
    }
}