package com.example.bookedUp.controller;

import com.example.bookedUp.dto.PropertyDTO;
import com.example.bookedUp.model.Property;
import com.example.bookedUp.model.Host;
import com.example.bookedUp.service.PropertyService;
import com.example.bookedUp.mapper.PropertyMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private final PropertyService propertyService;
    private final PropertyMapper propertyMapper;

    public PropertyController(PropertyService propertyService, PropertyMapper propertyMapper) {
        this.propertyService = propertyService;
        this.propertyMapper = propertyMapper;
    }

    @PostMapping
    public ResponseEntity<PropertyDTO> createProperty(@RequestBody PropertyDTO propertyDTO) {
        Property property = propertyMapper.toEntity(propertyDTO);
        Property createdProperty = propertyService.createProperty(property);
        PropertyDTO responseDTO = propertyMapper.toDTO(createdProperty);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id) {
        Optional<Property> property = propertyService.getPropertyById(id);
        return property.map(p -> ResponseEntity.ok(propertyMapper.toDTO(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        List<PropertyDTO> dtos = properties.stream()
                .map(propertyMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity<List<PropertyDTO>> getPropertiesByHost(@PathVariable Long hostId) {
        Host host = new Host();
        host.setId(hostId);
        List<Property> properties = propertyService.getPropertiesByHost(host);
        List<PropertyDTO> dtos = properties.stream()
                .map(propertyMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyDTO> updateProperty(
            @PathVariable Long id,
            @RequestBody PropertyDTO propertyDTO) {
        Property property = propertyMapper.toEntity(propertyDTO);
        Property updatedProperty = propertyService.updateProperty(id, property);
        PropertyDTO responseDTO = propertyMapper.toDTO(updatedProperty);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyService.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/images")
    public ResponseEntity<Void> addImageToProperty(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile image) {
    
        String imageUrl = "https://example.com/images/" + image.getOriginalFilename();
        propertyService.addImageToProperty(id, imageUrl);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<PropertyDTO>> searchProperties(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer guests) {
        List<Property> properties = propertyService.searchProperties(
                location, minPrice, maxPrice, guests);
        List<PropertyDTO> dtos = properties.stream()
                .map(propertyMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }
} 