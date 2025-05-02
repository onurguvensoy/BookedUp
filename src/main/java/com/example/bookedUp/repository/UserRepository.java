package com.example.bookedUp.repository;

import com.example.bookedUp.model.User;
import com.example.bookedUp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r.name = :roleType")
    List<User> findByRoles_Name(@Param("roleType") Role.RoleType roleType);
    
    boolean existsByEmail(String email);
    boolean existsByIdAndEmail(Long id, String email);
    
    @Query("SELECT u FROM User u WHERE TYPE(u) = Host")
    List<User> findAllHosts();
    
    @Query("SELECT u FROM User u WHERE TYPE(u) = Guest")
    List<User> findAllGuests();
    
    @Query("SELECT u FROM User u WHERE TYPE(u) = Admin")
    List<User> findAllAdmins();
} 