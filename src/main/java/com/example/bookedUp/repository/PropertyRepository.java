package com.example.bookedUp.repository;

import com.example.bookedUp.model.Property;
import com.example.bookedUp.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> findById(Long id);
    List<Property> findAll();
    List<Property> findByHost(Host host);
} 