package com.example.bookedUp.factory;

import com.example.bookedUp.model.Reservation;
import com.example.bookedUp.model.Property;
import com.example.bookedUp.model.Guest;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Component
public class ReservationFactoryImpl implements ReservationFactory {
    @Override
    public Reservation createReservation(
            Property property,
            Guest guest,
            LocalDate checkInDate,
            LocalDate checkOutDate,
            int numberOfGuests,
            String specialRequests) {
        
        long numberOfNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        BigDecimal totalPrice = property.getPricePerNight().multiply(BigDecimal.valueOf(numberOfNights));
        
        return Reservation.builder()
                .property(property)
                .guest(guest)
                .checkInDate(checkInDate)
                .checkOutDate(checkOutDate)
                .numberOfGuests(numberOfGuests)
                .totalPrice(totalPrice)
                .status("PENDING")
                .specialRequests(specialRequests)
                .build();
    }
} 