package com.example.bookUp.facade;

import com.example.bookUp.model.Reservation;
import com.example.bookUp.model.ReservationStatus;
import com.example.bookUp.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationFacadeImpl implements ReservationFacade {
    private final ReservationRepository reservationRepository;

    public ReservationFacadeImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Transactional
    public Reservation createReservation(Reservation reservation) {
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
    public List<Reservation> getReservationsByGuestId(Long guestId) {
        return reservationRepository.findByGuestId(guestId);
    }

    @Override
    public List<Reservation> getReservationsByHostId(Long hostId) {
        return reservationRepository.findByHostId(hostId);
    }

    @Override
    @Transactional
    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setCheckInDate(reservationDetails.getCheckInDate());
                    reservation.setCheckOutDate(reservationDetails.getCheckOutDate());
                    reservation.setTotalPrice(reservationDetails.getTotalPrice());
                    return reservationRepository.save(reservation);
                })
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Reservation updateReservationStatus(Long id, String status) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setStatus(ReservationStatus.valueOf(status.toUpperCase()));
                    return reservationRepository.save(reservation);
                })
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + id));
    }
} 