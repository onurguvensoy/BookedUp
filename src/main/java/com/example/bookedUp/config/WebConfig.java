package com.example.bookedUp.config;

import com.example.bookedUp.interceptor.RoleBasedAccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RoleBasedAccessInterceptor roleBasedAccessInterceptor;

    public WebConfig(RoleBasedAccessInterceptor roleBasedAccessInterceptor) {
        this.roleBasedAccessInterceptor = roleBasedAccessInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(roleBasedAccessInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/auth/**");
    }
} 