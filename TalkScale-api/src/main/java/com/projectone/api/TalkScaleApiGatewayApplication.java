package com.projectone.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@SpringBootApplication
@EnableFeignClients(basePackages = "com.projectone.api.client")
@SpringBootApplication(
        exclude = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration.class
        }
)
public class TalkScaleApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(TalkScaleApiGatewayApplication.class, args);
    }
}
