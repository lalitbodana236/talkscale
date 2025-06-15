package com.projectone.api.config;



import com.projectone.api.filter.GatewayJwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class GatewaySecurityConfig {
    
    private final GatewayJwtFilter jwtAuthFilter;
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("jwtAuthFilter "+jwtAuthFilter);
        return http
                       .csrf(AbstractHttpConfigurer::disable)
                       .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                       .authorizeHttpRequests(auth -> auth
                                                              .requestMatchers("/api/v1/auth/**","/error").permitAll()
                                                              .anyRequest().authenticated())
                       .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                       .build();
    }
}


