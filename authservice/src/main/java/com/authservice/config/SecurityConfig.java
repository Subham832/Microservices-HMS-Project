package com.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //   SecuirtyConfigFile - to Permit All request
    //    What to keep in Configuration file
    //  1. url open
    //   2. url to authenticate
    //    3. Authorization

    @Bean
    public SecurityFilterChain securityConfig1(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                req -> {
//                    req.anyRequest().permitAll();
                    req.requestMatchers("/api/v1/auth/welcome").permitAll()
                            .anyRequest().authenticated();
                }
        );
        return http.build();

    }
}
