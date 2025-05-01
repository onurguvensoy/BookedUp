package com.example.bookedUp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "admins")
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends User {
    @Column(name = "phone_number")
    private String phoneNumber;

    @PrePersist
    @Override
    protected void onCreate() {
        super.onCreate();
        if (getRoles() == null || getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(Role.RoleType.ADMIN));
            setRoles(roles);
        }
    }
} 