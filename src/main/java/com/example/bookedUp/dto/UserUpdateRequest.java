package com.example.bookedUp.dto;

import com.example.bookedUp.model.Role;
import lombok.Data;
import java.util.Set;

@Data
public class UserUpdateRequest {
    private String email;
    private String firstName;
    private String lastName;
    private Set<Role.RoleType> roles;
} 