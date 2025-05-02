package com.example.bookedUp.facade;

import com.example.bookedUp.model.Role;
import java.util.Set;

public interface RoleFacade {
    Role getOrCreateRole(Role.RoleType roleType);
    Set<Role> getRolesByTypes(Set<Role.RoleType> roleTypes);
} 