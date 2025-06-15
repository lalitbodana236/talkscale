package com.projectone.api.controller;

import com.projectone.api.client.UserServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    
    private final UserServiceClient userAuthClient;
    
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        System.out.println("Forwarding findAll to user-service");
        return userAuthClient.findALl();
    }
}
