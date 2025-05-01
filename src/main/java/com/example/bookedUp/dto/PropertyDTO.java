package com.example.bookedUp.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PropertyDTO {
    private String title;
    private String description;
    private String address;
    private BigDecimal pricePerNight;
    private int maxGuests;
    private int bedrooms;
    private int bathrooms;
    private Long hostId;
    private String propertyType; // "APARTMENT", "VILLA", "HOSTEL"
    
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
    
    private List<String> imageUrls;
} 