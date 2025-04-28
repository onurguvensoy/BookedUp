package com.example.bookedUp.service;

import com.example.bookedUp.dto.ReservationDto;
import java.util.List;

public interface ReservationService {
    ReservationDto createReservation(ReservationDto reservationDto);
    ReservationDto getReservationById(Long id);
    List<ReservationDto> getAllReservations();
    List<ReservationDto> getReservationsByGuestId(Long guestId);
    List<ReservationDto> getReservationsByHostId(Long hostId);
    ReservationDto updateReservation(Long id, ReservationDto reservationDto);
    void deleteReservation(Long id);
    ReservationDto updateReservationStatus(Long id, String status);
} 