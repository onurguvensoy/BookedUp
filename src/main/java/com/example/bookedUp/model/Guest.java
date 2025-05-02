package com.example.bookedUp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Builder;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "guests")
@DiscriminatorValue("GUEST")
@PrimaryKeyJoinColumn(name = "id")
public class Guest extends User {
    @Column(name = "phone_number")
    @Builder.Default
    private String phoneNumber = "";

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Reservation> reservations = new ArrayList<>();

    @PrePersist
    @Override
    protected void onCreate() {
        super.onCreate();
        if (getRoles() == null || getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(Role.RoleType.GUEST));
            setRoles(roles);
        }
    }
} 