package com.projectone.user.filter;


import com.projectone.user.security.CustomUserDetailsService;
import com.projectone.user.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        
        final String authHeader = request.getHeader("Authorization");
        String jwt = null;
        String username = null;
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7); // Strip "Bearer "
            username = jwtUtil.extractUsername(jwt);
        }
        
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            System.out.printf("user ", userDetails.getUsername());
            if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        
        filterChain.doFilter(request, response);
    }
}

