package com.paymentservice; // Root package for the Payment Service Spring Boot application.

import org.springframework.boot.SpringApplication; // Bootstraps and launches the application.
import org.springframework.boot.autoconfigure.SpringBootApplication; // Enables auto-configuration and component scanning.
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; // Registers this service with Eureka (or another registry).
import org.springframework.cloud.openfeign.EnableFeignClients; // Enables Feign client scanning for inter-service communication.

/**
 * PaymentserviceApplication is the entry point for the Payment Service.
 * It enables service discovery via Eureka and REST communication via OpenFeign.
 */
@SpringBootApplication
@EnableDiscoveryClient // Enables registration with a service discovery server (e.g., Eureka).
@EnableFeignClients // Scans for interfaces annotated with @FeignClient.
public class PaymentserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentserviceApplication.class, args); // Launches the Spring Boot application.
	}
}
