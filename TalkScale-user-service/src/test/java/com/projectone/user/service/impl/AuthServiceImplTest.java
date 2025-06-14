package com.projectone.user.service.impl;


import com.projectone.user.dto.LoginRequest;
import com.projectone.user.dto.RegisterRequest;
import com.projectone.user.entity.AppUser;
import com.projectone.user.entity.Role;
import com.projectone.user.repository.IUserRepository;
import com.projectone.user.service.impl.impl.AuthServiceImpl;
import com.projectone.user.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceImplTest {
    
    @Mock
    private IUserRepository userRepository;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    @Mock
    private JwtUtil jwtUtil;
    
    @Mock
    private AuthenticationManager authenticationManager;
    
    @InjectMocks
    private AuthServiceImpl authService;
    
    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private AppUser user;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        registerRequest = RegisterRequest.builder()
                                  .fullName("John Doe")
                                  .email("john.doe@example.com")
                                  .password("strongPass123")
                                  .enabled(true)
                                  .role(Role.USER)
                                  .build();
        loginRequest = new LoginRequest("john@example.com", "strongPass123");
        
        user = AppUser.builder()
                       .email("john@example.com")
                       .password("hashedPassword")
                       .fullName("John Doe")
                       .build();
    }
    
    @Test
    void testRegisterUser_success() {
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword");
        when(jwtUtil.generateToken(anyString())).thenReturn("mock-token");
        
        Map<String, Object> response = authService.registerUser(registerRequest);
        
        verify(userRepository).save(any(AppUser.class));
        assertEquals("mock-token", response.get("token"));
        assertEquals("john@example.com", response.get("user"));
    }
    
    @Test
    void testAuthenticateUser_success() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "hashedPassword")).thenReturn(true);
        when(jwtUtil.generateToken("john@example.com")).thenReturn("mock-token");
        
        Map<String, Object> response = authService.authenticateUser(loginRequest);
        
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        assertEquals("mock-token", response.get("token"));
        assertEquals("john@example.com", response.get("user"));
    }
    
    @Test
    void testAuthenticateUser_userNotFound() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.empty());
        
        Exception exception = assertThrows(RuntimeException.class, () ->
                                                                           authService.authenticateUser(loginRequest));
        
        assertTrue(exception.getMessage().contains("User not found"));
    }
    
    @Test
    void testAuthenticateUser_invalidPassword() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "hashedPassword")).thenReturn(false);
        
        Exception exception = assertThrows(RuntimeException.class, () ->
                                                                           authService.authenticateUser(loginRequest));
        
        assertTrue(exception.getMessage().contains("Invalid credentials"));
    }
    
    @Test
    void testAuthenticateUser_authenticationFails() {
        when(userRepository.findByEmail("john@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "hashedPassword")).thenReturn(true);
        doThrow(new BadCredentialsException("Bad credentials"))
                .when(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        
        Exception exception = assertThrows(RuntimeException.class, () ->
                                                                           authService.authenticateUser(loginRequest));
        
        assertTrue(exception.getMessage().contains("Invalid credentials"));
    }
}

