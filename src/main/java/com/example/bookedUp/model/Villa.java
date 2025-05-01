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
@Table(name = "villas")
@PrimaryKeyJoinColumn(name = "id")
public class Villa extends Property {
    @Column(name = "has_pool")
    private boolean hasPool;

    @Column(name = "has_garden")
    private boolean hasGarden;

    @Column(name = "total_land_area")
    private double totalLandArea;
} 