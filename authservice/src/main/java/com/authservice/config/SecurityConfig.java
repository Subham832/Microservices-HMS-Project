package com.authservice.config;

import com.authservice.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Injected CustomUserDetailsService for retrieving user details from the database
     */
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * Publicly accessible endpoints that do not require authentication
     */
    private final String[] openUrl = {
            "/api/v1/auth/register",
            "/api/v1/auth/login",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/webjars/**"
    };

    /**
     * Bean for password encoding using BCrypt
     * Used to hash and verify passwords securely
     */
    @Bean
    public PasswordEncoder getEcodedPassword() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean for AuthenticationManager
     * Delegates authentication process to configured AuthenticationProvider
     */
    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configures the AuthenticationProvider
     * Uses DaoAuthenticationProvider with custom UserDetailsService and password encoder
     */
    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(getEcodedPassword());
        return authProvider;
    }

    /**
     * Configures the HTTP security filter chain
     * - Disables CSRF protection (for stateless APIs)
     * - Allows unauthenticated access to specified open URLs
     * - Requires authentication for other requests
     * - Secures specific route with role-based authorization
     */
    @Bean
    public SecurityFilterChain securityConfig1(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(req -> {
                    req
                            .requestMatchers(openUrl).permitAll()
                            .requestMatchers("/api/v1/welcome/message").hasAnyRole("USER", "ADMIN")
                            .anyRequest().authenticated();
                }).authenticationProvider(authProvider()) // Add authentication provider
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter before UsernamePasswordAuthenticationFilter
//                .httpBasic(); // Basic auth (can be replaced with JWT later)
        return http.build(); // Return the configured SecurityFilterChain
    }
}
