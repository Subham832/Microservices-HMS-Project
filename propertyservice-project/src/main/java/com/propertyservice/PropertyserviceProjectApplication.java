package com.propertyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PropertyserviceProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertyserviceProjectApplication.class, args);
    }

}
