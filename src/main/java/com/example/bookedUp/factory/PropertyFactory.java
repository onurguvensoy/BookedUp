package com.example.bookedUp.factory;

import com.example.bookedUp.model.*;
import java.math.BigDecimal;

public interface PropertyFactory {
    Property createProperty(
        String title,
        String description,
        String address,
        BigDecimal pricePerNight,
        int maxGuests,
        int bedrooms,
        int bathrooms,
        Host host
    );

    Apartment createApartment(
        String title,
        String description,
        String address,
        BigDecimal pricePerNight,
        int maxGuests,
        int bedrooms,
        int bathrooms,
        Host host,
        Integer floorNumber,
        Boolean hasElevator,
        String apartmentNumber
    );

    Villa createVilla(
        String title,
        String description,
        String address,
        BigDecimal pricePerNight,
        int maxGuests,
        int bedrooms,
        int bathrooms,
        Host host,
        Boolean hasPool,
        Boolean hasGarden,
        Double totalLandArea
    );

    Hostel createHostel(
        String title,
        String description,
        String address,
        BigDecimal pricePerNight,
        int maxGuests,
        int bedrooms,
        int bathrooms,
        Host host,
        Integer totalRooms,
        Boolean hasCommonKitchen,
        Boolean hasCommonLivingRoom
    );
} 