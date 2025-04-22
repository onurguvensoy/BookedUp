package com.example.bookedUp.Service.Impl;

import com.example.bookedUp.Model.Rental;
import com.example.bookedUp.Model.Reservation;
import com.example.bookedUp.Model.User;
import com.example.bookedUp.Model.Guest;
import com.example.bookedUp.Model.Villa;
import com.example.bookedUp.Repository.ReservationRepository;
import com.example.bookedUp.Service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;

    @Override
    public Reservation createReservation(User user, Rental rental, int days) {
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRental(rental);
        reservation.setDays(days);
        reservation.setTotalPrice(rental.getPricePerNight() * days);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation reserve(User user, Rental rental, int days) {
        // Call createReservation method
        return createReservation(user, rental, days);
    }
}
