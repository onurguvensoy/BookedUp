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
@Table(name = "hostels")
@PrimaryKeyJoinColumn(name = "id")
public class Hostel extends Property {
    @Column(name = "total_rooms")
    private int totalRooms;

    @Column(name = "has_common_kitchen")
    private boolean hasCommonKitchen;

    @Column(name = "has_common_living_room")
    private boolean hasCommonLivingRoom;
} 