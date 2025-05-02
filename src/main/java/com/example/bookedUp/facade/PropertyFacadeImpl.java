package com.example.bookedUp.facade;

import com.example.bookedUp.model.Property;
import com.example.bookedUp.model.Host;
import com.example.bookedUp.service.PropertyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyFacadeImpl implements PropertyFacade {
    private final PropertyService propertyService;

    public PropertyFacadeImpl(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    @Transactional
    public Property createProperty(Property property) {
        return propertyService.createProperty(property);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Property> getPropertyById(Long id) {
        return propertyService.getPropertyById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Property> getPropertiesByHost(Host host) {
        return propertyService.getPropertiesByHost(host);
    }

    @Override
    @Transactional
    public Property updateProperty(Long id, Property propertyDetails) {
        return propertyService.updateProperty(id, propertyDetails);
    }

    @Override
    @Transactional
    public void deleteProperty(Long id) {
        propertyService.deleteProperty(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Property> searchProperties(String location, Integer minPrice, Integer maxPrice, Integer guests) {
        return propertyService.searchProperties(location, minPrice, maxPrice, guests);
    }
} 