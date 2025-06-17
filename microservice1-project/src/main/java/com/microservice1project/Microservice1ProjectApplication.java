package com.microservice1project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Microservice1ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Microservice1ProjectApplication.class, args);
	}

}
