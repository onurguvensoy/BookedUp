package com.example.bookedUp.facade;

import com.example.bookedUp.model.Reservation;
import java.util.List;
import java.util.Optional;

public interface ReservationFacade {
    Reservation createReservation(Reservation reservation);
    Optional<Reservation> getReservationById(Long id);
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsByGuestId(Long guestId);
    List<Reservation> getReservationsByHostId(Long hostId);
    Reservation updateReservation(Long id, Reservation reservationDetails);
    void deleteReservation(Long id);
    Reservation updateReservationStatus(Long id, String status);
} 