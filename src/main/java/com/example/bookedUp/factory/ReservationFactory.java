package com.example.bookedUp.factory;

import com.example.bookedUp.model.Reservation;
import com.example.bookedUp.model.User;
import java.time.LocalDateTime;

public interface ReservationFactory {
    Reservation createReservation(
            User guest,
            User host,
            LocalDateTime checkInDate,
            LocalDateTime checkOutDate,
            double totalPrice,
            String status
    );
} 