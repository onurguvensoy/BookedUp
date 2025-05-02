package com.example.bookedUp.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class PropertyDTO {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Address is required")
    private String address;

    @NotNull(message = "Price per night is required")
    @Positive(message = "Price per night must be positive")
    private BigDecimal pricePerNight;

    @NotNull(message = "Maximum guests is required")
    @Min(value = 1, message = "Maximum guests must be at least 1")
    private int maxGuests;

    @NotNull(message = "Number of bedrooms is required")
    @Min(value = 0, message = "Number of bedrooms cannot be negative")
    private int bedrooms;

    @NotNull(message = "Number of bathrooms is required")
    @Min(value = 0, message = "Number of bathrooms cannot be negative")
    private int bathrooms;

    @NotNull(message = "Host ID is required")
    @Positive(message = "Host ID must be positive")
    private Long hostId;

    @NotBlank(message = "Property type is required")
    @Pattern(regexp = "^(APARTMENT|VILLA|HOSTEL)$", message = "Property type must be APARTMENT, VILLA, or HOSTEL")
    private String propertyType;
    
    // Apartment specific fields
    private Integer floorNumber;
    private Boolean hasElevator;
    private String apartmentNumber;
    
    // Villa specific fields
    private Boolean hasPool;
    private Boolean hasGarden;
    private Double totalLandArea;
    
    // Hostel specific fields
    private Integer totalRooms;
    private Boolean hasCommonKitchen;
    private Boolean hasCommonLivingRoom;
} 