package com.projectone.api.client;

import com.projectone.api.config.FeignClientInterceptor;
import com.projectone.common.dto.LoginRequest;
import com.projectone.common.dto.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "talkscale-user-service",
        url = "http://localhost:8081", // or use service discovery name
        configuration = FeignClientInterceptor.class
)
public interface AuthServiceClient {
    @PostMapping("/api/v1/auth/login")
    ResponseEntity<?> login(@RequestBody LoginRequest request);
    
    @PostMapping("/api/v1/auth/register")
    ResponseEntity<?> register(@RequestBody RegisterRequest request);
    
}
