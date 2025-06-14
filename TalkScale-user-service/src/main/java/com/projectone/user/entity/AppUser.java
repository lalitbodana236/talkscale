package com.projectone.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String email;
    
    private String password;
    
    private boolean enabled;
    
    private String fullName;
    
    private String username;
    
    @Enumerated(EnumType.STRING)
    private Role role; // Role must implement GrantedAuthority
}
