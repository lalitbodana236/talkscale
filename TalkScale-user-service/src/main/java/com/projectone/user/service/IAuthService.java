package com.projectone.user.service;

import com.projectone.user.dto.LoginRequest;
import com.projectone.user.dto.RegisterRequest;

import java.util.Map;

public interface IAuthService {
    Map<String, Object> registerUser(RegisterRequest request);
    
    Map<String, Object> authenticateUser(LoginRequest request);
    //Abstracts logic for registration, login
}
