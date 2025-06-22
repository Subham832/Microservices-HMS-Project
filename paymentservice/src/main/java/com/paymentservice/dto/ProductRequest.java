package com.paymentservice.dto; // Defines the DTO package for payment-related data models.

import lombok.AllArgsConstructor; // Generates constructor with all fields.
import lombok.Data; // Generates getters, setters, toString, equals, and hashCode.
import lombok.NoArgsConstructor; // Generates no-argument constructor.

/**
 * ProductRequest is a DTO used to initiate a Stripe Checkout session.
 * It contains payment amount, quantity, product name, currency, and booking ID reference.
 */
@Data
@AllArgsConstructor // Generates all-args constructor.
@NoArgsConstructor // Generates no-args constructor.
public class ProductRequest {

    private Long amount; // Payment amount in the smallest currency unit (e.g., paise if INR).
    private Long quantity; // Number of items being purchased.
    private String name; // Name of the product/service.
    private String currency; // ISO currency code (e.g., "inr", "usd").
    private long bookingId; // Booking ID used to update status post-payment.
}
