package com.bookingservice.dto;
// Declares the package for this class.

public class Rooms {
// Defines a public class named Rooms.

    private long id;
    // Private field to store the unique identifier of the room.

    private String roomType;
    // Private field to store the type of the room (e.g., single, double, suite).

    private double basePrice;
    // Private field to store the base price of the room.

    // Getter method for 'id' field.
    public long getId() {
        return id;
    }

    // Getter method for 'roomType' field.
    public String getRoomType() {
        return roomType;
    }

    // Getter method for 'basePrice' field.
    public double getBasePrice() {
        return basePrice;
    }

    // Setter method for 'id' field.
    public void setId(long id) {
        this.id = id;
    }

    // Setter method for 'roomType' field.
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    // Setter method for 'basePrice' field.
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
}
