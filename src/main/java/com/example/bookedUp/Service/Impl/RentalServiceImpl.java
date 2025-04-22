package com.example.bookedUp.Service.Impl;

import com.example.bookedUp.Factory.RentalFactory;
import com.example.bookedUp.Model.Rental;
import com.example.bookedUp.Service.RentalService;
import org.springframework.stereotype.Service;

@Service
public class RentalServiceImpl implements RentalService {
    @Override
    public Rental createRental(String type, String address, double price) {
        Rental rental = RentalFactory.createRental(type);
        rental.setAddress(address);
        rental.setPricePerNight(price);
        return rental;
    }
}