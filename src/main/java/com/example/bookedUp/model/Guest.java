package com.example.bookedUp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "guests")
@PrimaryKeyJoinColumn(name = "id")
public class Guest extends User {
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "default_address")
    private String defaultAddress;

    @PrePersist
    @Override
    protected void onCreate() {
        super.onCreate();
        if (getRole() == null) {
            setRole(Role.GUEST);
        }
    }
} 