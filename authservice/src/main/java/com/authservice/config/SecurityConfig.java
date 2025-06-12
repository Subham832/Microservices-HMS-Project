package com.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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


    private String[] openUrl =
            {
                    "/api/v1/auth/register",
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
    public SecurityFilterChain securityConfig1(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF
                .authorizeHttpRequests(req ->
                        {
//                          req.anyRequest().permitAll();
                            req.requestMatchers(openUrl).permitAll()
                                    .anyRequest().authenticated();
                        }
                );
        return http.build();

    }
}
