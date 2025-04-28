package com.example.bookUp.controller;

import com.example.bookUp.dto.ReservationDto;
import com.example.bookUp.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationDto) {
        ReservationDto createdReservation = reservationService.createReservation(reservationDto);
        return ResponseEntity.ok(createdReservation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable Long id) {
        ReservationDto reservation = reservationService.getReservationById(id);
        return ResponseEntity.ok(reservation);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = reservationService.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByGuestId(@PathVariable Long guestId) {
        List<ReservationDto> reservations = reservationService.getReservationsByGuestId(guestId);
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByHostId(@PathVariable Long hostId) {
        List<ReservationDto> reservations = reservationService.getReservationsByHostId(hostId);
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateReservation(
            @PathVariable Long id,
            @RequestBody ReservationDto reservationDto) {
        ReservationDto updatedReservation = reservationService.updateReservation(id, reservationDto);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ReservationDto> updateReservationStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        ReservationDto updatedReservation = reservationService.updateReservationStatus(id, status);
        return ResponseEntity.ok(updatedReservation);
    }
} 