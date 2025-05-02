package com.example.bookedUp.service;

import com.example.bookedUp.model.Property;
import com.example.bookedUp.model.Host;
import com.example.bookedUp.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    @Transactional
    public Property createProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Property> getPropertiesByHost(Host host) {
        return propertyRepository.findByHost(host);
    }

    @Override
    @Transactional
    public Property updateProperty(Long id, Property propertyDetails) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Property not found with id: " + id));
        
        property.setTitle(propertyDetails.getTitle());
        property.setDescription(propertyDetails.getDescription());
        property.setAddress(propertyDetails.getAddress());
        property.setPricePerNight(propertyDetails.getPricePerNight());
        property.setMaxGuests(propertyDetails.getMaxGuests());
        property.setBedrooms(propertyDetails.getBedrooms());
        property.setBathrooms(propertyDetails.getBathrooms());
        property.setHost(propertyDetails.getHost());
        
        return propertyRepository.save(property);
    }

    @Override
    @Transactional
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Property> searchProperties(String location, Integer minPrice, Integer maxPrice, Integer guests) {
        List<Property> allProperties = propertyRepository.findAll();
        
        return allProperties.stream()
                .filter(property -> 
                    (location == null || property.getAddress().toLowerCase().contains(location.toLowerCase())) &&
                    (minPrice == null || property.getPricePerNight().compareTo(new BigDecimal(minPrice)) >= 0) &&
                    (maxPrice == null || property.getPricePerNight().compareTo(new BigDecimal(maxPrice)) <= 0) &&
                    (guests == null || property.getMaxGuests() >= guests)
                )
                .collect(Collectors.toList());
    }
} 