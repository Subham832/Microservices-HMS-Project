package com.bookingservice;
// Declares the package this class belongs to.

import org.springframework.boot.SpringApplication;
// Imports Spring Boot class to bootstrap and launch the application.

import org.springframework.boot.autoconfigure.SpringBootApplication;
// Imports annotation to enable Spring Boot's auto-configuration and component scanning.

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
// Imports annotation to enable service registration and discovery (e.g., with Eureka).

import org.springframework.cloud.openfeign.EnableFeignClients;
// Imports annotation to enable Feign clients for declarative REST client support.

@SpringBootApplication
// Marks this class as the main Spring Boot application entry point,
// enabling auto-configuration and component scanning.

@EnableFeignClients
// Enables detection of interfaces annotated with @FeignClient to create REST clients automatically.

@EnableDiscoveryClient
// Enables this application to register with a service registry like Eureka for service discovery.

public class BookingserviceProjectApplication {
// Defines the main class for the booking service application.

	public static void main(String[] args) {
		// Main method - entry point of the Java application.

		SpringApplication.run(BookingserviceProjectApplication.class, args);
		// Launches the Spring Boot application, starting the embedded server and initializing Spring context.
	}

}
