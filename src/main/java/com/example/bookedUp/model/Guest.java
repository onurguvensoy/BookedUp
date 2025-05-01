package com.example.bookedUp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.Builder;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "guests")
@PrimaryKeyJoinColumn(name = "id")
public class Guest extends User {
    @Column(name = "phone_number")
    @Builder.Default
    private String phoneNumber = "";

    @Column(name = "default_address")
    @Builder.Default
    private String defaultAddress = "";

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