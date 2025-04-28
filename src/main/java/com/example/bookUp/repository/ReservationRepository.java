package com.example.bookUp.repository;

import com.example.bookUp.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByGuestId(Long guestId);
    List<Reservation> findByHostId(Long hostId);
} 