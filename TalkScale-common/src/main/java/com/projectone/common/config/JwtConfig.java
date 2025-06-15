package com.projectone.common.config;

import com.projectone.common.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();  // Or inject required args if any
    }
}

