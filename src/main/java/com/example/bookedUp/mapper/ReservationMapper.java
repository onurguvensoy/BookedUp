package com.example.bookedUp.mapper;

import com.example.bookedUp.dto.ReservationDTO;
import com.example.bookedUp.model.Reservation;
import com.example.bookedUp.model.Property;
import com.example.bookedUp.model.Guest;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    public Reservation toEntity(ReservationDTO dto) {
        Property property = new Property();
        property.setId(dto.getPropertyId());

        Guest guest = new Guest();
        guest.setId(dto.getGuestId());

        return Reservation.builder()
                .property(property)
                .guest(guest)
                .checkInDate(dto.getCheckInDate())
                .checkOutDate(dto.getCheckOutDate())
                .numberOfGuests(dto.getNumberOfGuests())
                .totalPrice(dto.getTotalPrice())
                .status(dto.getStatus())
                .specialRequests(dto.getSpecialRequests())
                .build();
    }

    public ReservationDTO toDTO(Reservation reservation) {
        ReservationDTO dto = new ReservationDTO();
        dto.setId(reservation.getId());
        dto.setPropertyId(reservation.getProperty().getId());
        dto.setGuestId(reservation.getGuest().getId());
        dto.setCheckInDate(reservation.getCheckInDate());
        dto.setCheckOutDate(reservation.getCheckOutDate());
        dto.setNumberOfGuests(reservation.getNumberOfGuests());
        dto.setTotalPrice(reservation.getTotalPrice());
        dto.setStatus(reservation.getStatus());
        dto.setSpecialRequests(reservation.getSpecialRequests());
        return dto;
    }
} 