package com.example.bookedUp.dto;

import com.example.bookedUp.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
} 