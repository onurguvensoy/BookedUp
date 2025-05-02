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
@Table(name = "hosts")
@DiscriminatorValue("HOST")
@PrimaryKeyJoinColumn(name = "id")
public class Host extends User {
    @Column(name = "phone_number")
    @Builder.Default
    private String phoneNumber = "";

    @Column(name = "bank_account")
    @Builder.Default
    private String bankAccount = "";

    @Column(name = "address")
    private String address;

    @Column(name = "is_available")
    @Builder.Default
    private boolean available = true;

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Property> properties = new ArrayList<>();

    @PrePersist
    @Override
    protected void onCreate() {
        super.onCreate();
        if (getRoles() == null || getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(Role.RoleType.HOST));
            setRoles(roles);
        }
    }
} 