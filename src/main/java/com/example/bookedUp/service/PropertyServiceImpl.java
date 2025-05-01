package com.example.bookedUp.service;

import com.example.bookedUp.facade.PropertyFacade;
import com.example.bookedUp.model.Property;
import com.example.bookedUp.model.Host;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Service
public class PropertyServiceImpl implements PropertyService {
    private final PropertyFacade propertyFacade;

    public PropertyServiceImpl(PropertyFacade propertyFacade) {
        this.propertyFacade = propertyFacade;
    }

    @Override
    @Transactional
    public Property createProperty(Property property) {
        return propertyFacade.createProperty(property);
    }

    @Override
    public Optional<Property> getPropertyById(Long id) {
        return propertyFacade.getPropertyById(id);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyFacade.getAllProperties();
    }

    @Override
    public List<Property> getPropertiesByHost(Host host) {
        return propertyFacade.getPropertiesByHost(host);
    }

    @Override
    @Transactional
    public Property updateProperty(Long id, Property propertyDetails) {
        return propertyFacade.updateProperty(id, propertyDetails);
    }

    @Override
    @Transactional
    public void deleteProperty(Long id) {
        propertyFacade.deleteProperty(id);
    }

    @Override
    @Transactional
    public void addImageToProperty(Long propertyId, String imageUrl) {
        propertyFacade.addImageToProperty(propertyId, imageUrl);
    }

    @Override
    public List<Property> searchProperties(String location, Integer minPrice, Integer maxPrice, Integer guests) {
        List<Property> allProperties = propertyFacade.getAllProperties();
        
        return allProperties.stream()
                .filter(property -> 
                    (location == null || property.getAddress().toLowerCase().contains(location.toLowerCase())) &&
                    (minPrice == null || property.getPricePerNight().compareTo(new BigDecimal(minPrice)) >= 0) &&
                    (maxPrice == null || property.getPricePerNight().compareTo(new BigDecimal(maxPrice)) <= 0) &&
                    (guests == null || property.getMaxGuests() >= guests)
                )
                .toList();
    }
} 