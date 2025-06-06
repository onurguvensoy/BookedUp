package com.example.bookedUp.repository;

import com.example.bookedUp.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface HostRepository extends JpaRepository<Host, Long> {
    Optional<Host> findById(Long id);
} 