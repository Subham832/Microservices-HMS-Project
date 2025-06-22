package com.bookingservice.dto;
// Declares the package this class belongs to.

import lombok.AllArgsConstructor;
// Imports Lombok annotation to generate a constructor with all fields.

import lombok.Builder;
// Imports Lombok annotation to implement the builder pattern for this class.

import lombok.Data;
// Imports Lombok annotation to generate getters, setters, toString, equals, and hashCode methods.

import lombok.NoArgsConstructor;
// Imports Lombok annotation to generate a no-argument constructor.

@Data
// Lombok annotation to generate getters, setters, toString, equals, and hashCode methods automatically.

@AllArgsConstructor
// Lombok annotation to generate a constructor with all fields as parameters.

@NoArgsConstructor
// Lombok annotation to generate a no-argument constructor.

@Builder
// Lombok annotation to implement builder pattern for easy object creation.

public class StripeResponse {
// Defines a public class named StripeResponse.

    private String status;
    // Field to store the status of the Stripe operation (e.g., "success" or "failed").

    private String message;
    // Field to store any message related to the Stripe operation.

    private String sessionId;
    // Field to store the Stripe session ID related to a payment session.

    private String sessionUrl;
    // Field to store the URL for the Stripe checkout session.
}
