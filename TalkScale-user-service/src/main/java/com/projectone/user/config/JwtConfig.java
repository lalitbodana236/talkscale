package com.projectone.user.config;

import com.projectone.common.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(); // Customize if needed
    }
}
