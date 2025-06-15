package com.authservice.config;

import com.authservice.service.CustomUserDetailsService;
import com.authservice.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter { // Implementing the OncePerRequestFilter class to filter incoming requests
    @Autowired
    private JWTService jwtService;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");// Get the JWT token from the request header
//        System.out.println(authHeader); // Print the JWT token for debugging purposes
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);// Remove the "Bearer " prefix from the token
//            System.out.println(jwt); // Print the JWT token for debugging purposes
            String username = jwtService.validateTokenAndRetrieveSubject(jwt);// Validate and retrieve the subject from the JWT token
//            System.out.println(username); // Print the subject for debugging purposes
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) { // If the subject is not authenticated, authenticate it
                var userDetails = userDetailsService.loadUserByUsername(username); // Load the user details from the database
                var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); // Create a UsernamePasswordAuthenticationToken (Using Java 10 Features Here)
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Set the authentication details
                SecurityContextHolder.getContext().setAuthentication(authToken); // Set the authentication context
            }
        }
        filterChain.doFilter(request, response); // Pass the request and response to the next filter in the chain
    }
}
