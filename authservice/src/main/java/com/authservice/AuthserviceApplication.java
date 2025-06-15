package com.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Authentication Service Spring Boot application.
 * <p>
 * This class bootstraps the application using Spring Boot auto-configuration.
 */
@SpringBootApplication
public class AuthserviceApplication {

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args command-line arguments passed during application startup
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthserviceApplication.class, args);
    }
}
