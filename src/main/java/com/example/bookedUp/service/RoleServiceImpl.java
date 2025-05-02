package com.example.bookedUp.service;

import com.example.bookedUp.model.Role;
import com.example.bookedUp.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public Role getOrCreateRole(Role.RoleType roleType) {
        return roleRepository.findByName(roleType)
                .orElseGet(() -> {
                    Role role = new Role(roleType);
                    return roleRepository.save(role);
                });
    }

    @Override
    @Transactional
    public Set<Role> getRolesByTypes(Set<Role.RoleType> roleTypes) {
        return roleTypes.stream()
                .map(this::getOrCreateRole)
                .collect(Collectors.toSet());
    }
} 