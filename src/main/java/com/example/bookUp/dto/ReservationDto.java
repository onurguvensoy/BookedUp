package com.example.bookUp.dto;

import com.example.bookUp.model.ReservationStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private Long guestId;
    private Long hostId;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private double totalPrice;
    private ReservationStatus status;
    private LocalDateTime createdAt;
} 