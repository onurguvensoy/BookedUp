package com.example.bookedUp.mapper;

import com.example.bookedUp.dto.PropertyDTO;
import com.example.bookedUp.model.*;
import com.example.bookedUp.factory.PropertyFactory;
import org.springframework.stereotype.Component;

@Component
public class PropertyMapper {
    private final PropertyFactory propertyFactory;

    public PropertyMapper(PropertyFactory propertyFactory) {
        this.propertyFactory = propertyFactory;
    }

    public Property toEntity(PropertyDTO dto) {
        Host host = new Host();
        host.setId(dto.getHostId());

        switch (dto.getPropertyType().toUpperCase()) {
            case "APARTMENT":
                return propertyFactory.createApartment(
                    dto.getTitle(),
                    dto.getDescription(),
                    dto.getAddress(),
                    dto.getPricePerNight(),
                    dto.getMaxGuests(),
                    dto.getBedrooms(),
                    dto.getBathrooms(),
                    host,
                    dto.getFloorNumber(),
                    dto.getHasElevator(),
                    dto.getApartmentNumber()
                );
            case "VILLA":
                return propertyFactory.createVilla(
                    dto.getTitle(),
                    dto.getDescription(),
                    dto.getAddress(),
                    dto.getPricePerNight(),
                    dto.getMaxGuests(),
                    dto.getBedrooms(),
                    dto.getBathrooms(),
                    host,
                    dto.getHasPool(),
                    dto.getHasGarden(),
                    dto.getTotalLandArea()
                );
            case "HOSTEL":
                return propertyFactory.createHostel(
                    dto.getTitle(),
                    dto.getDescription(),
                    dto.getAddress(),
                    dto.getPricePerNight(),
                    dto.getMaxGuests(),
                    dto.getBedrooms(),
                    dto.getBathrooms(),
                    host,
                    dto.getTotalRooms(),
                    dto.getHasCommonKitchen(),
                    dto.getHasCommonLivingRoom()
                );
            default:
                throw new IllegalArgumentException("Invalid property type: " + dto.getPropertyType());
        }
    }

    public PropertyDTO toDTO(Property property) {
        PropertyDTO dto = new PropertyDTO();
        dto.setTitle(property.getTitle());
        dto.setDescription(property.getDescription());
        dto.setAddress(property.getAddress());
        dto.setPricePerNight(property.getPricePerNight());
        dto.setMaxGuests(property.getMaxGuests());
        dto.setBedrooms(property.getBedrooms());
        dto.setBathrooms(property.getBathrooms());
        dto.setHostId(property.getHost().getId());

        if (property instanceof Apartment apartment) {
            dto.setPropertyType("APARTMENT");
            dto.setFloorNumber(apartment.getFloorNumber());
            dto.setHasElevator(apartment.isHasElevator());
            dto.setApartmentNumber(apartment.getApartmentNumber());
        } else if (property instanceof Villa villa) {
            dto.setPropertyType("VILLA");
            dto.setHasPool(villa.isHasPool());
            dto.setHasGarden(villa.isHasGarden());
            dto.setTotalLandArea(villa.getTotalLandArea());
        } else if (property instanceof Hostel hostel) {
            dto.setPropertyType("HOSTEL");
            dto.setTotalRooms(hostel.getTotalRooms());
            dto.setHasCommonKitchen(hostel.isHasCommonKitchen());
            dto.setHasCommonLivingRoom(hostel.isHasCommonLivingRoom());
        }

        dto.setImageUrls(property.getImages().stream()
                .map(PropertyImage::getImageUrl)
                .toList());

        return dto;
    }
} 