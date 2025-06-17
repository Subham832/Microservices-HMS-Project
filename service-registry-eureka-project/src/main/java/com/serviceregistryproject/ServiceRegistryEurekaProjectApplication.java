package com.serviceregistryproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication // Indicates that this is a Spring Boot application
@EnableEurekaServer // Enables Eureka server functionality
public class ServiceRegistryEurekaProjectApplication { // Entry point for the application

    public static void main(String[] args) {
        SpringApplication.run(ServiceRegistryEurekaProjectApplication.class, args); // Starts the Spring Boot application
    } // End of main method

} // End of ServiceRegistryEurekaProjectApplication class