package com.projectone.common.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    
    @NotBlank(message = "first name is required")
    private String firstname;
    
    @NotBlank(message = "last name is required")
    private String lastname;
    
   
    @NotBlank(message = "username is required")
    private String username;
    
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message = "mobile is required")
    private String mobile;
    
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    
    // Future extensibility: phone number, referral code, etc.
}
