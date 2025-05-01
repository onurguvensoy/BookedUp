package com.example.bookedUp.facade;

import com.example.bookedUp.model.*;
import com.example.bookedUp.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyFacadeImpl implements PropertyFacade {
    private final PropertyRepository propertyRepository;

    public PropertyFacadeImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    @Transactional
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public List<Property> getPropertiesByHost(Host host) {
        return propertyRepository.findByHost(host);
    }

    @Override
    @Transactional
    public Property updateProperty(Long id, Property propertyDetails) {
        return propertyRepository.findById(id)
                .map(property -> {
                    property.setTitle(propertyDetails.getTitle());
                    property.setDescription(propertyDetails.getDescription());
                    property.setAddress(propertyDetails.getAddress());
                    property.setPricePerNight(propertyDetails.getPricePerNight());
                    property.setMaxGuests(propertyDetails.getMaxGuests());
                    property.setBedrooms(propertyDetails.getBedrooms());
                    property.setBathrooms(propertyDetails.getBathrooms());
                    property.setAvailable(propertyDetails.isAvailable());
                    return propertyRepository.save(property);
                })
                .orElseThrow(() -> new IllegalArgumentException("Property not found with id: " + id));
    }

    @Override
    @Transactional
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void addImageToProperty(Long propertyId, String imageUrl) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property not found with id: " + propertyId));

        PropertyImage image = PropertyImage.builder()
                .imageUrl(imageUrl)
                .property(property)
                .build();

        property.getImages().add(image);
        propertyRepository.save(property);
    }
} 