package com.example.bookedUp.Service;

import com.example.bookedUp.Model.Reservation;
import com.example.bookedUp.Model.Rental;
import com.example.bookedUp.Model.User;

public interface ReservationService {
    Reservation createReservation(User user, Rental rental, int days);  // For creating a reservation
    Reservation reserve(User user, Rental rental, int days);           // For finalizing the reservation (optional)
}
