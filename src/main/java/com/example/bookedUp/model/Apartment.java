package com.example.bookedUp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "apartments")
@PrimaryKeyJoinColumn(name = "id")
public class Apartment extends Property {
    @Column(name = "floor_number")
    private int floorNumber;

    @Column(name = "has_elevator")
    private boolean hasElevator;

    @Column(name = "apartment_number")
    private String apartmentNumber;
} 