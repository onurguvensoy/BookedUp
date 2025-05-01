package com.example.bookedUp.controller;

import com.example.bookedUp.dto.ReservationDTO;
import com.example.bookedUp.model.Reservation;
import com.example.bookedUp.model.Property;
import com.example.bookedUp.model.Guest;
import com.example.bookedUp.service.ReservationService;
import com.example.bookedUp.mapper.ReservationMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public ReservationController(ReservationService reservationService, ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = reservationMapper.toEntity(reservationDTO);
        Reservation createdReservation = reservationService.createReservation(reservation);
        ReservationDTO responseDTO = reservationMapper.toDTO(createdReservation);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.getReservationById(id);
        return reservation.map(r -> ResponseEntity.ok(reservationMapper.toDTO(r)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        List<ReservationDTO> dtos = reservations.stream()
                .map(reservationMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByProperty(@PathVariable Long propertyId) {
        Property property = new Property();
        property.setId(propertyId);
        List<Reservation> reservations = reservationService.getReservationsByProperty(property);
        List<ReservationDTO> dtos = reservations.stream()
                .map(reservationMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByGuest(@PathVariable Long guestId) {
        Guest guest = new Guest();
        guest.setId(guestId);
        List<Reservation> reservations = reservationService.getReservationsByGuest(guest);
        List<ReservationDTO> dtos = reservations.stream()
                .map(reservationMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ReservationDTO> updateReservationStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        Reservation updatedReservation = reservationService.updateReservationStatus(id, status);
        ReservationDTO responseDTO = reservationMapper.toDTO(updatedReservation);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/property/{propertyId}/availability")
    public ResponseEntity<Boolean> checkPropertyAvailability(
            @PathVariable Long propertyId,
            @RequestParam LocalDate checkInDate,
            @RequestParam LocalDate checkOutDate) {
        Property property = new Property();
        property.setId(propertyId);
        boolean isAvailable = reservationService.isPropertyAvailable(property, checkInDate, checkOutDate);
        return ResponseEntity.ok(isAvailable);
    }
} 