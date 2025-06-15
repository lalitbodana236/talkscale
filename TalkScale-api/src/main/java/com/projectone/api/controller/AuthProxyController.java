package com.projectone.api.controller;


import com.projectone.api.client.AuthServiceClient;
import com.projectone.common.dto.LoginRequest;
import com.projectone.common.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthProxyController {

//    private final RestTemplate restTemplate = new RestTemplate(); // For now, replace later with FeignClient
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Object request) {
//        System.out.println("inside api gateway login "+request);
//        String url = "http://localhost:8081/api/v1/auth/login"; // user-service port
//        System.out.println("inside api gateway login "+url);
//        return restTemplate.postForEntity(url, request, Object.class);
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody Object request) {
//        System.out.println("inside api gateway register "+request);
//        String url = "http://localhost:8081/api/v1/auth/register";
//        System.out.println("inside api gateway register "+url);
//        return restTemplate.postForEntity(url, request, Object.class);
//    }
    
    private final AuthServiceClient authClient;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        System.out.println("Forwarding login to user-service");
        return authClient.login(request);
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        System.out.println("Forwarding registration to user-service");
        return authClient.register(request);
    }
}

