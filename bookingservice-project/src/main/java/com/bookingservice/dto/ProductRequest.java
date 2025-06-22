package com.bookingservice.dto;
// Declares the package in which this class belongs.

import lombok.AllArgsConstructor;
// Imports Lombok annotation to generate a constructor with all fields.

import lombok.Data;
// Imports Lombok annotation to generate getters, setters, toString, equals, and hashCode methods.

import lombok.NoArgsConstructor;
// Imports Lombok annotation to generate a no-argument constructor.

@Data
// Lombok annotation to automatically generate getters, setters, toString, equals, and hashCode methods for all fields.

@AllArgsConstructor
// Lombok annotation to automatically generate a constructor with parameters for all fields.

@NoArgsConstructor
// Lombok annotation to automatically generate a no-argument constructor.

public class ProductRequest {
    // Defines a public class named ProductRequest.

    private Long amount;
    // Private field to store the amount, using Long wrapper type.

    private Long quantity;
    // Private field to store the quantity, using Long wrapper type.

    private String name;
    // Private field to store the name of the product as a String.

    private String currency;
    // Private field to store the currency type as a String.

    private long bookingId;
    // Private field to store the booking ID, using primitive long type.
}
