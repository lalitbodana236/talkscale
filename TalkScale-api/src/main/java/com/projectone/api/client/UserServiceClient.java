package com.projectone.api.client;


import com.projectone.api.config.FeignClientInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "talkscale-user-service",
        url = "http://localhost:8081", // or use service discovery name
        configuration = FeignClientInterceptor.class
)
public interface UserServiceClient {
    
    
    @GetMapping("/api/v1/user/findAll")
    ResponseEntity<?> findALl();
}

