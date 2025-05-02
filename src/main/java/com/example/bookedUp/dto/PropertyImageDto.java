package com.example.bookedUp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertyImageDto {
    private Long id;
    private String imageUrl;
    private Long propertyId;
} 