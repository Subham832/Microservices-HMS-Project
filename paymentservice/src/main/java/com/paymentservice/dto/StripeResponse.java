package com.paymentservice.dto; // Defines the DTO package for payment-related responses.

import lombok.AllArgsConstructor; // Generates constructor with all fields.
import lombok.Builder; // Enables builder pattern for object creation.
import lombok.Data; // Generates getters, setters, equals, hashCode, and toString.
import lombok.NoArgsConstructor; // Generates no-argument constructor.

/**
 * StripeResponse is a DTO used to return Stripe checkout session details
 * after initiating a payment. It includes session status, ID, and redirect URL.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StripeResponse {

    private String status; // Status of the session creation (e.g., "success", "failure").
    private String message; // Optional descriptive message (e.g., error or success info).
    private String sessionId; // Stripe session ID.
    private String sessionUrl; // URL to redirect the client to complete the payment.
}
