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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //   SecuirtyConfigFile - to Permit All request
    //    What to keep in Configuration file
    //  1. url open
    //   2. url to authenticate
    //    3. Authorization

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    private String[] openUrl =
            {
                    "/api/v1/auth/register",
                    "/api/v1/auth/login",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/swagger-resources/**",
                    "/webjars/**"
            };

    @Bean
    public PasswordEncoder getEcodedPassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(getEcodedPassword());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityConfig1(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF
                .authorizeHttpRequests
                        (req ->
                        {
//                          req.anyRequest().permitAll();
                            req.requestMatchers(openUrl).permitAll()
                                    .requestMatchers("/api/v1/welcome/message").hasRole("USER")
                                    .anyRequest().authenticated();
                        }).httpBasic();
        return http.build();

    }
}
