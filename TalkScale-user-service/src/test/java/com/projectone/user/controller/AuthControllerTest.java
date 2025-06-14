package com.projectone.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectone.user.config.AuthTestConfig;
import com.projectone.user.dto.LoginRequest;
import com.projectone.user.dto.RegisterRequest;
import com.projectone.user.service.IAuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import(AuthTestConfig.class)
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private IAuthService authService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    
    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest("john@example.com", "password123", "John Doe");
        loginRequest = new LoginRequest("john@example.com", "password123");
    }
    
    @Test
    void testRegister_returnsToken() throws Exception {
        Map<String, Object> mockResponse = Map.of(
                "status", "success",
                "token", "mock-token"
        );
        
        Mockito.when(authService.registerUser(any())).thenReturn(mockResponse);
        
        mockMvc.perform(post("/api/auth/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.token").value("mock-token"));
    }
    
    @Test
    void testLogin_returnsToken() throws Exception {
        Map<String, Object> mockResponse = Map.of(
                "status", "success",
                "token", "mock-token"
        );
        
        Mockito.when(authService.authenticateUser(any())).thenReturn(mockResponse);
        
        mockMvc.perform(post("/api/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.token").value("mock-token"));
    }
}