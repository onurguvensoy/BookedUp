package com.example.bookedUp.facade;

import com.example.bookedUp.model.Property;
import com.example.bookedUp.model.Host;
import java.util.List;
import java.util.Optional;

public interface PropertyFacade {
    Property createProperty(Property property);
    Optional<Property> getPropertyById(Long id);
    List<Property> getAllProperties();
    List<Property> getPropertiesByHost(Host host);
    Property updateProperty(Long id, Property propertyDetails);
    void deleteProperty(Long id);
    List<Property> searchProperties(String location, Integer minPrice, Integer maxPrice, Integer guests);
} 