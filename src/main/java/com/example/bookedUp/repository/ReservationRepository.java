package com.example.bookedUp.repository;

import com.example.bookedUp.model.Reservation;
import com.example.bookedUp.model.Property;
import com.example.bookedUp.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findById(Long id);
    List<Reservation> findAll();
    List<Reservation> findByProperty(Property property);
    List<Reservation> findByGuest(Guest guest);
} 