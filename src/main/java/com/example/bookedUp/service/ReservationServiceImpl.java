package com.example.bookedUp.service;

import com.example.bookedUp.dto.ReservationDTO;
import com.example.bookedUp.facade.ReservationFacade;
import com.example.bookedUp.model.Reservation;
import com.example.bookedUp.model.Property;
import com.example.bookedUp.model.Guest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationFacade reservationFacade;

    @Override
    @Transactional
    public Reservation createReservation(Reservation reservation) {
        return reservationFacade.createReservation(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reservation> getReservationById(Long id) {
        return reservationFacade.getReservationById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getAllReservations() {
        return reservationFacade.getAllReservations();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getReservationsByProperty(Property property) {
        return reservationFacade.getReservationsByProperty(property);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> getReservationsByGuest(Guest guest) {
        return reservationFacade.getReservationsByGuest(guest);
    }

    @Override
    @Transactional
    public Reservation updateReservationStatus(Long id, String status) {
        return reservationFacade.updateReservationStatus(id, status);
    }

    @Override
    @Transactional
    public void cancelReservation(Long id) {
        reservationFacade.cancelReservation(id);
    }

    @Override
    public boolean isPropertyAvailable(Property property, LocalDate checkInDate, LocalDate checkOutDate) {
        return reservationFacade.isPropertyAvailable(property, checkInDate, checkOutDate);
    }
} 