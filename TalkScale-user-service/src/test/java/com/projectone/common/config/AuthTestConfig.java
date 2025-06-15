package com.projectone.common.config;

import com.projectone.user.service.IAuthService;
import com.projectone.common.util.JwtUtil;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AuthTestConfig {
    
    @Bean
    public IAuthService authService() {
        return Mockito.mock(IAuthService.class);
    }
    
    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
