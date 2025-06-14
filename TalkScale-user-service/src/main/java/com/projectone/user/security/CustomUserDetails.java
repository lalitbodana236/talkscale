package com.projectone.user.security;

import com.projectone.user.entity.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@AllArgsConstructor
@Data
@Builder
public class CustomUserDetails implements UserDetails {
    private final AppUser user;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return Collections.singleton(user.getRole()); // Ensure Role implements GrantedAuthority
//    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getRole().getAuthority()));
    }
    
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    
    @Override
    public String getUsername() {
        return user.getEmail(); // Login via email
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true; // Customize based on user fields if needed
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
