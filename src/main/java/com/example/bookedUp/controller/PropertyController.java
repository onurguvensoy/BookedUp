package com.example.bookedUp.controller;

import com.example.bookedUp.dto.PropertyDTO;
import com.example.bookedUp.model.Property;
import com.example.bookedUp.model.Host;
import com.example.bookedUp.facade.PropertyFacade;
import com.example.bookedUp.mapper.PropertyMapper;
import com.example.bookedUp.repository.HostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private final PropertyFacade propertyFacade;
    private final PropertyMapper propertyMapper;
    private final HostRepository hostRepository;

    public PropertyController(PropertyFacade propertyFacade, PropertyMapper propertyMapper, HostRepository hostRepository) {
        this.propertyFacade = propertyFacade;
        this.propertyMapper = propertyMapper;
        this.hostRepository = hostRepository;
    }

    @PostMapping
    public ResponseEntity<PropertyDTO> createProperty(@RequestBody PropertyDTO propertyDTO) {
        // Validate host exists and is enabled
        Host host = hostRepository.findById(propertyDTO.getHostId())
                .orElseThrow(() -> new IllegalArgumentException("Host not found with id: " + propertyDTO.getHostId()));
        
        if (!host.isEnabled()) {
            throw new IllegalArgumentException("Host account is not enabled. Please contact support.");
        }

        Property property = propertyMapper.toEntity(propertyDTO);
        Property createdProperty = propertyFacade.createProperty(property);
        PropertyDTO responseDTO = propertyMapper.toDTO(createdProperty);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getPropertyById(@PathVariable Long id) {
        Optional<Property> property = propertyFacade.getPropertyById(id);
        if (property.isPresent()) {
            PropertyDTO dto = propertyMapper.toDTO(property.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getAllProperties() {
        List<Property> properties = propertyFacade.getAllProperties();
        List<PropertyDTO> dtos = properties.stream()
                .map(propertyMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity<List<PropertyDTO>> getPropertiesByHost(@PathVariable Long hostId) {
        Host host = hostRepository.findById(hostId)
                .orElseThrow(() -> new IllegalArgumentException("Host not found with id: " + hostId));
        
        List<Property> properties = propertyFacade.getPropertiesByHost(host);
        List<PropertyDTO> dtos = properties.stream()
                .map(propertyMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyDTO> updateProperty(@PathVariable Long id, @RequestBody PropertyDTO propertyDTO) {
        Property property = propertyMapper.toEntity(propertyDTO);
        Property updatedProperty = propertyFacade.updateProperty(id, property);
        PropertyDTO responseDTO = propertyMapper.toDTO(updatedProperty);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {
        propertyFacade.deleteProperty(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<PropertyDTO>> searchProperties(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false) Integer guests) {
        
        List<Property> properties = propertyFacade.searchProperties(location, minPrice, maxPrice, guests);
        List<PropertyDTO> dtos = properties.stream()
                .map(propertyMapper::toDTO)
                .toList();
        return ResponseEntity.ok(dtos);
    }
} 