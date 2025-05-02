package com.example.bookedUp.mapper;

import com.example.bookedUp.dto.PropertyDTO;
import com.example.bookedUp.model.*;
import com.example.bookedUp.factory.PropertyFactory;
import com.example.bookedUp.repository.HostRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PropertyMapper {
    private final PropertyFactory propertyFactory;
    private final HostRepository hostRepository;

    public PropertyMapper(PropertyFactory propertyFactory, HostRepository hostRepository) {
        this.propertyFactory = propertyFactory;
        this.hostRepository = hostRepository;
    }

    @Transactional
    public Property toEntity(PropertyDTO dto) {
        Host host = hostRepository.findById(dto.getHostId())
                .orElseThrow(() -> new IllegalArgumentException("Host not found with id: " + dto.getHostId()));

        switch (dto.getPropertyType()) {
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

        if (property instanceof Apartment) {
            Apartment apartment = (Apartment) property;
            dto.setPropertyType("APARTMENT");
            dto.setFloorNumber(apartment.getFloorNumber());
            dto.setHasElevator(apartment.isHasElevator());
            dto.setApartmentNumber(apartment.getApartmentNumber());
        } else if (property instanceof Villa) {
            Villa villa = (Villa) property;
            dto.setPropertyType("VILLA");
            dto.setHasPool(villa.isHasPool());
            dto.setHasGarden(villa.isHasGarden());
            dto.setTotalLandArea(villa.getTotalLandArea());
        } else if (property instanceof Hostel) {
            Hostel hostel = (Hostel) property;
            dto.setPropertyType("HOSTEL");
            dto.setTotalRooms(hostel.getTotalRooms());
            dto.setHasCommonKitchen(hostel.isHasCommonKitchen());
            dto.setHasCommonLivingRoom(hostel.isHasCommonLivingRoom());
        }

        return dto;
    }
} 