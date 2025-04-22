package com.example.bookedUp.Facade;

import com.example.bookedUp.Model.Reservation;
import com.example.bookedUp.Model.Rental;
import com.example.bookedUp.Model.User;
import com.example.bookedUp.Service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationFacade {
    private final ReservationService reservationService;

    // This method will create a reservation and return the Reservation object.
    public Reservation createReservation(User user, Rental rental, int days) {
        return reservationService.createReservation(user, rental, days);
    }

    // This method can also be used for a more comprehensive reservation process (e.g., payment, availability check)
    public Reservation reserve(User user, Rental rental, int days) {
        return reservationService.reserve(user, rental, days);
    }
}
