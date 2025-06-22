package com.propertyservice; // Root package for the Property Service Spring Boot application.

import org.springframework.boot.SpringApplication; // Bootstraps and launches the Spring Boot application.
import org.springframework.boot.autoconfigure.SpringBootApplication; // Marks this as a Spring Boot application with auto-configuration.
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; // Enables service registration with Eureka or another discovery server.

/**
 * PropertyserviceProjectApplication is the entry point for the Spring Boot application.
 * It enables auto-configuration and service discovery via Eureka.
 */
@SpringBootApplication // Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan.
@EnableDiscoveryClient // Enables discovery client for service registration and discovery.
public class PropertyserviceProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertyserviceProjectApplication.class, args); // Starts the Spring Boot application.
    }
}
