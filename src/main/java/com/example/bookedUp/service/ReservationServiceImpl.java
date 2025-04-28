package com.example.bookedUp.service;

import com.example.bookedUp.dto.ReservationDto;
import com.example.bookedUp.facade.ReservationFacade;
import com.example.bookedUp.model.Reservation;
import com.example.bookedUp.model.ReservationStatus;
import com.example.bookedUp.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationFacade reservationFacade;

    @Override
    @Transactional
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation reservation = convertToEntity(reservationDto);
        Reservation createdReservation = reservationFacade.createReservation(reservation);
        return convertToDto(createdReservation);
    }

    @Override
    @Transactional(readOnly = true)
    public ReservationDto getReservationById(Long id) {
        return reservationFacade.getReservationById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationDto> getAllReservations() {
        return reservationFacade.getAllReservations().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationDto> getReservationsByGuestId(Long guestId) {
        return reservationFacade.getReservationsByGuestId(guestId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservationDto> getReservationsByHostId(Long hostId) {
        return reservationFacade.getReservationsByHostId(hostId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReservationDto updateReservation(Long id, ReservationDto reservationDto) {
        Reservation reservation = convertToEntity(reservationDto);
        Reservation updatedReservation = reservationFacade.updateReservation(id, reservation);
        return convertToDto(updatedReservation);
    }

    @Override
    @Transactional
    public void deleteReservation(Long id) {
        reservationFacade.deleteReservation(id);
    }

    @Override
    @Transactional
    public ReservationDto updateReservationStatus(Long id, String status) {
        Reservation updatedReservation = reservationFacade.updateReservationStatus(id, status);
        return convertToDto(updatedReservation);
    }

    private ReservationDto convertToDto(Reservation reservation) {
        ReservationDto dto = new ReservationDto();
        dto.setId(reservation.getId());
        dto.setGuestId(reservation.getGuest().getId());
        dto.setHostId(reservation.getHost().getId());
        dto.setCheckInDate(reservation.getCheckInDate());
        dto.setCheckOutDate(reservation.getCheckOutDate());
        dto.setTotalPrice(reservation.getTotalPrice());
        dto.setStatus(reservation.getStatus());
        dto.setCreatedAt(reservation.getCreatedAt());
        return dto;
    }

    private Reservation convertToEntity(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setCheckInDate(reservationDto.getCheckInDate());
        reservation.setCheckOutDate(reservationDto.getCheckOutDate());
        reservation.setTotalPrice(reservationDto.getTotalPrice());
        reservation.setStatus(reservationDto.getStatus());
        reservation.setCreatedAt(reservationDto.getCreatedAt());
        return reservation;
    }
} 