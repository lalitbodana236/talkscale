package com.projectone.user.dto;

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
    
    @NotBlank(message = "Full name is required")
    private String fullName;
    
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;
    
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    
    // Future extensibility: phone number, referral code, etc.
}
