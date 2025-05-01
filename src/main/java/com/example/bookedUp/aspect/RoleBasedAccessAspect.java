package com.example.bookedUp.aspect;

import com.example.bookedUp.annotation.RequireRole;
import com.example.bookedUp.model.Role;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class RoleBasedAccessAspect {

    @Before("@annotation(requireRole)")
    public void checkRoleAccess(JoinPoint joinPoint, RequireRole requireRole) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            List<Role.RoleType> requiredRoles = Arrays.asList(requireRole.value());
            attributes.getRequest().setAttribute("requiredRoles", requiredRoles);
        }
    }
} 