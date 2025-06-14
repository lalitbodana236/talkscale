package com.projectone.user.security;

import com.projectone.user.entity.AppUser;
import com.projectone.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    
    private final IUserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("befor loadUserByUsername " + username);
        AppUser user = userRepository.findByEmail(username)
                               .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        System.out.printf("after loadUserByUsername " + user.getEmail());
        
        //return new CustomUserDetails(user);
        return CustomUserDetails.builder()
                       .user(user)
                       .build();
    }
}

