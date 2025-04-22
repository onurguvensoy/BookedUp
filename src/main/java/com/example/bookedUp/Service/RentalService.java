package com.example.bookedUp.Service;

import com.example.bookedUp.Model.Rental;

public interface RentalService {
    Rental createRental(String type, String address, double price);
}