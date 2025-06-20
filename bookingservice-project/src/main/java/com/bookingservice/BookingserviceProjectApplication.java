package com.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BookingserviceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingserviceProjectApplication.class, args);
	}

}
