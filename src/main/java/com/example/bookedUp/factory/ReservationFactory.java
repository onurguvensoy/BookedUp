package com.example.bookedUp.factory;

import com.example.bookedUp.model.Reservation;
import com.example.bookedUp.model.Property;
import com.example.bookedUp.model.Guest;
import java.time.LocalDate;

public interface ReservationFactory {
    Reservation createReservation(
        Property property,
        Guest guest,
        LocalDate checkInDate,
        LocalDate checkOutDate,
        int numberOfGuests,
        String specialRequests
    );
} 