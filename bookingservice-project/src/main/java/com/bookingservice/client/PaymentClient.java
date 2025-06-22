package com.bookingservice.client; // Defines the package for Feign clients in the Booking Service.

import com.bookingservice.dto.ProductRequest; // DTO for sending payment details.
import com.bookingservice.dto.StripeResponse; // DTO for receiving Stripe session response.

import org.springframework.cloud.openfeign.FeignClient; // Enables declarative REST client with OpenFeign.
import org.springframework.web.bind.annotation.PostMapping; // Maps HTTP POST requests.
import org.springframework.web.bind.annotation.RequestBody; // Binds request body to method parameter.

/**
 * PaymentClient is a Feign client used to interact with the Payment Service.
 * It sends payment initiation requests and receives Stripe session information.
 */
@FeignClient(name = "PAYMENTSERVICE") // Registers this client to communicate with the Payment Service.
public interface PaymentClient {

    @PostMapping("/product/v1/checkout") // Maps to the payment service's Stripe checkout endpoint.
    StripeResponse checkoutProducts(@RequestBody ProductRequest productRequest); // Sends product data and receives session info.
}
