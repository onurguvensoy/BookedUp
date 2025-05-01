package com.example.bookedUp.service;

import com.example.bookedUp.model.Role;
import java.util.Set;

public interface RoleService {
    Role getOrCreateRole(Role.RoleType roleType);
    Set<Role> getRolesByTypes(Set<Role.RoleType> roleTypes);
} 