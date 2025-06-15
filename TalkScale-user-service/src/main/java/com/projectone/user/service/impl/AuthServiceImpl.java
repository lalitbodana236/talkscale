package com.projectone.user.service.impl;

import com.projectone.common.dto.LoginRequest;
import com.projectone.common.dto.RegisterRequest;
import com.projectone.common.util.JwtUtil;
import com.projectone.user.entity.AppUser;
import com.projectone.user.entity.Role;
import com.projectone.user.repository.IUserRepository;
import com.projectone.user.service.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {
    
    
    
        
        private final IUserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtUtil jwtUtil;
        private final AuthenticationManager authenticationManager;
        
        @Override
        public Map<String, Object> registerUser(RegisterRequest request) {
            AppUser user = AppUser.builder()
                                   .email(request.getEmail())
                                   .password(passwordEncoder.encode(request.getPassword()))
                                   .firstname(request.getFirstname())
                                   .lastname(request.getLastname())
                                   .username(request.getUsername())
                                   .mobile(request.getMobile())
                                   .enabled(true)
                                   .role(Role.USER)
                                   .emailVerified(false)
                                   .mobileVerified(false)
                                   .build();
            userRepository.save(user);
            
            String token = jwtUtil.generateToken(user.getEmail());
            return Map.of("token", token, "user", user.getEmail());
        }
    
    @Override
    public Map<String, Object> authenticateUser(LoginRequest request) {
        System.out.println("credentials "+ request.getEmail());
        AppUser user1 = userRepository.findByEmail(request.getEmail())
                            .orElseThrow(() -> new RuntimeException("User not found with email: " + request.getEmail()));
        System.out.println("user1 "+user1);
        System.out.println("mathcer "+passwordEncoder.matches(request.getPassword(), user1.getPassword()));
        if (!passwordEncoder.matches(request.getPassword(), user1.getPassword())) {
            throw new RuntimeException("Invalid credentials for user: " + request.getEmail());
        }
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//            );
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid credentials");
        }
        System.out.println("user find ");
            AppUser user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + request.getEmail()));
        System.out.println("befor generation  "+user.getEmail());
            String token = jwtUtil.generateToken(user.getEmail());
        System.out.println("befor generation token "+token);
            return Map.of("token", token, "user", user.getEmail());
        }
    }

