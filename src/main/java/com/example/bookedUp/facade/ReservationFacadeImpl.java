package com.example.bookedUp.facade;

import com.example.bookedUp.model.Reservation;
import com.example.bookedUp.model.Property;
import com.example.bookedUp.model.Guest;
import com.example.bookedUp.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@Service
public class ReservationFacadeImpl implements ReservationFacade {
    private final ReservationRepository reservationRepository;

    public ReservationFacadeImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional
    public Reservation createReservation(Reservation reservation) {
        if (!isPropertyAvailable(reservation.getProperty(), 
                reservation.getCheckInDate(), 
                reservation.getCheckOutDate())) {
            throw new IllegalArgumentException("Property is not available for the selected dates");
        }
        return reservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> getReservationsByProperty(Property property) {
        return reservationRepository.findByProperty(property);
    }

    @Override
    public List<Reservation> getReservationsByGuest(Guest guest) {
        return reservationRepository.findByGuest(guest);
    }

    @Override
    @Transactional
    public Reservation updateReservationStatus(Long id, String status) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setStatus(status);
                    return reservationRepository.save(reservation);
                })
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + id));
    }

    @Override
    @Transactional
    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + id));
        
        if (!reservation.getStatus().equals("PENDING")) {
            throw new IllegalStateException("Only pending reservations can be cancelled");
        }
        
        reservation.setStatus("CANCELLED");
        reservationRepository.save(reservation);
    }

    @Override
    public boolean isPropertyAvailable(Property property, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Reservation> existingReservations = reservationRepository.findByProperty(property);
        
        return existingReservations.stream()
                .noneMatch(reservation -> 
                    !reservation.getStatus().equals("CANCELLED") &&
                    !(checkOutDate.isBefore(reservation.getCheckInDate()) || 
                      checkInDate.isAfter(reservation.getCheckOutDate()))
                );
    }
} 