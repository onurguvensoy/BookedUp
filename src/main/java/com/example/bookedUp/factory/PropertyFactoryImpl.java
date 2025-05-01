package com.example.bookedUp.factory;

import com.example.bookedUp.model.*;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
public class PropertyFactoryImpl implements PropertyFactory {
    @Override
    public Property createProperty(
        String title,
        String description,
        String address,
        BigDecimal pricePerNight,
        int maxGuests,
        int bedrooms,
        int bathrooms,
        Host host
    ) {
        return Property.builder()
            .title(title)
            .description(description)
            .address(address)
            .pricePerNight(pricePerNight)
            .maxGuests(maxGuests)
            .bedrooms(bedrooms)
            .bathrooms(bathrooms)
            .host(host)
            .build();
    }

    @Override
    public Apartment createApartment(
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
    ) {
        return Apartment.builder()
            .title(title)
            .description(description)
            .address(address)
            .pricePerNight(pricePerNight)
            .maxGuests(maxGuests)
            .bedrooms(bedrooms)
            .bathrooms(bathrooms)
            .host(host)
            .floorNumber(floorNumber)
            .hasElevator(hasElevator)
            .apartmentNumber(apartmentNumber)
            .build();
    }

    @Override
    public Villa createVilla(
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
    ) {
        return Villa.builder()
            .title(title)
            .description(description)
            .address(address)
            .pricePerNight(pricePerNight)
            .maxGuests(maxGuests)
            .bedrooms(bedrooms)
            .bathrooms(bathrooms)
            .host(host)
            .hasPool(hasPool)
            .hasGarden(hasGarden)
            .totalLandArea(totalLandArea)
            .build();
    }

    @Override
    public Hostel createHostel(
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
    ) {
        return Hostel.builder()
            .title(title)
            .description(description)
            .address(address)
            .pricePerNight(pricePerNight)
            .maxGuests(maxGuests)
            .bedrooms(bedrooms)
            .bathrooms(bathrooms)
            .host(host)
            .totalRooms(totalRooms)
            .hasCommonKitchen(hasCommonKitchen)
            .hasCommonLivingRoom(hasCommonLivingRoom)
            .build();
    }
} 