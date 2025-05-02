package com.example.bookedUp.facade;

import com.example.bookedUp.model.Role;
import com.example.bookedUp.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class RoleFacadeImpl implements RoleFacade {
    private final RoleService roleService;

    public RoleFacadeImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public Role getOrCreateRole(Role.RoleType roleType) {
        return roleService.getOrCreateRole(roleType);
    }

    @Override
    @Transactional
    public Set<Role> getRolesByTypes(Set<Role.RoleType> roleTypes) {
        return roleService.getRolesByTypes(roleTypes);
    }
} 