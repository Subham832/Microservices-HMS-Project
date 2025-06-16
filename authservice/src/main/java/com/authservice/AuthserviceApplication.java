package com.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
/**
 * Entry point for the Authentication Service Spring Boot application.
 * <p>
 * This class bootstraps the application using Spring Boot auto-configuration.
 */
@EnableDiscoveryClient
/**
 * Enables the service to register itself with a Eureka Discovery Server.
 * <p>
 * This annotation is essential for enabling service discovery in a microservices architecture.
 * Once registered, this service can be discovered and communicated with by other microservices
 * through the Eureka registry.
 */
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
