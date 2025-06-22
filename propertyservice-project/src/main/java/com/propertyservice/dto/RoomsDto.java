package com.propertyservice.dto; // Defines the package location for Data Transfer Objects (DTOs).

/**
 * RoomsDto is a Data Transfer Object representing individual room details within a property.
 * It includes the room's ID, type (e.g., single, double), and base price.
 */
public class RoomsDto {

    private long id; // Unique identifier for the room.
    private String roomType; // Type of room (e.g., Deluxe, Standard, Suite).
    private double basePrice; // Base price for the room per night.

    public long getId() { // Getter for room ID.
        return id;
    }

    public String getRoomType() { // Getter for room type.
        return roomType;
    }

    public void setId(long id) { // Setter for room ID.
        this.id = id;
    }

    public void setRoomType(String roomType) { // Setter for room type.
        this.roomType = roomType;
    }

    public double getBasePrice() { // Getter for room base price.
        return basePrice;
    }

    public void setBasePrice(double basePrice) { // Setter for room base price.
        this.basePrice = basePrice;
    }
}
