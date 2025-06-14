package com.projectone.user.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@TestConfiguration
public class AuthTestSecurityConfig {
    
    @Bean
    public SecurityFilterChain testSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // ✅ Disable CSRF for testing
                .authorizeHttpRequests(auth -> auth
                                                       .anyRequest().permitAll() // ✅ Allow all requests
                )
                .httpBasic(Customizer.withDefaults()); // optional, adds basic auth support for completeness
        
        return http.build();
    }
}
