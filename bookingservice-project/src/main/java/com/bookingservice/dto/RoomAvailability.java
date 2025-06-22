package com.bookingservice.dto;
// Declares the package this class belongs to.

import java.time.LocalDate;
// Imports the LocalDate class to handle date without time.

public class RoomAvailability {
// Defines a public class named RoomAvailability.

    private long id;
    // Private field to store the unique identifier of the room availability record.

    private LocalDate availableDate;
    // Private field to store the date on which the room availability is recorded.

    private int availableCount;
    // Private field to store the number of rooms available on the given date.

    private double price;
    // Private field to store the price for the room on the given date.

    private Rooms room;
    // Private field to reference the associated Rooms object (likely another DTO or entity).

    // Getter method for 'id' field.
    public long getId() {
        return id;
    }

    // Getter method for 'availableDate' field.
    public LocalDate getAvailableDate() {
        return availableDate;
    }

    // Getter method for 'availableCount' field.
    public int getAvailableCount() {
        return availableCount;
    }

    // Getter method for 'price' field.
    public double getPrice() {
        return price;
    }

    // Getter method for 'room' field.
    public Rooms getRoom() {
        return room;
    }

    // Setter method for 'id' field.
    public void setId(long id) {
        this.id = id;
    }

    // Setter method for 'availableDate' field.
    public void setAvailableDate(LocalDate availableDate) {
        this.availableDate = availableDate;
    }

    // Setter method for 'availableCount' field.
    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    // Setter method for 'price' field.
    public void setPrice(double price) {
        this.price = price;
    }

    // Setter method for 'room' field.
    public void setRoom(Rooms room) {
        this.room = room;
    }
}
