package com.example.bookedUp.service;

import com.example.bookedUp.model.Reservation;
import com.example.bookedUp.model.Property;
import com.example.bookedUp.model.Guest;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    Optional<Reservation> getReservationById(Long id);
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsByProperty(Property property);
    List<Reservation> getReservationsByGuest(Guest guest);
    Reservation updateReservationStatus(Long id, String status);
    void cancelReservation(Long id);
    boolean isPropertyAvailable(Property property, LocalDate checkInDate, LocalDate checkOutDate);
} 