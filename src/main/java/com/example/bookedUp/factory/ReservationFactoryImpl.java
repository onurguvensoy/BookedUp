package com.example.bookedUp.factory;

import com.example.bookedUp.model.Reservation;
import com.example.bookedUp.model.ReservationStatus;
import com.example.bookedUp.model.User;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class ReservationFactoryImpl implements ReservationFactory {
    @Override
    public Reservation createReservation(
            User guest,
            User host,
            LocalDateTime checkInDate,
            LocalDateTime checkOutDate,
            double totalPrice,
            String status) {
        Reservation reservation = new Reservation();
        reservation.setGuest(guest);
        reservation.setHost(host);
        reservation.setCheckInDate(checkInDate);
        reservation.setCheckOutDate(checkOutDate);
        reservation.setTotalPrice(totalPrice);
        reservation.setStatus(ReservationStatus.valueOf(status));
        return reservation;
    }
} 