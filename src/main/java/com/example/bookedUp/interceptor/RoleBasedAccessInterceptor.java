package com.example.bookedUp.interceptor;

import com.example.bookedUp.model.Role;
import com.example.bookedUp.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleBasedAccessInterceptor implements HandlerInterceptor {
    private final AuthService authService;

    public RoleBasedAccessInterceptor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid token");
            return false;
        }

        token = token.substring(7); // Remove "Bearer " prefix
        if (!authService.validateToken(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return false;
        }

        Long userId = authService.getUserIdFromToken(token);
        if (userId == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return false;
        }

        // Get required roles from the request attribute set by the controller
        @SuppressWarnings("unchecked")
        List<Role.RoleType> requiredRoles = (List<Role.RoleType>) request.getAttribute("requiredRoles");
        
        if (requiredRoles != null && !requiredRoles.isEmpty()) {
            // Get user's roles from the token
            String[] tokenParts = new String(java.util.Base64.getDecoder().decode(token)).split("\\|");
            String email = tokenParts[1];
            
            // In a real application, you would fetch the user's roles from the database
            // For simplicity, we'll assume the roles are stored in the token
            Set<Role.RoleType> userRoles = Arrays.stream(tokenParts[0].split(","))
                    .map(Role.RoleType::valueOf)
                    .collect(Collectors.toSet());

            // Check if user has any of the required roles
            boolean hasRequiredRole = requiredRoles.stream()
                    .anyMatch(userRoles::contains);

            if (!hasRequiredRole) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Insufficient permissions");
                return false;
            }
        }

        return true;
    }
} 