package com.propertyservice.entity; // Defines the package for JPA entity classes.

import java.time.LocalDate; // Represents a date without time zone.

import jakarta.persistence.*; // Imports JPA annotations for entity mapping.

/**
 * RoomAvailability is a JPA entity representing the availability and pricing of a room on a specific date.
 * It is linked to the Rooms entity and stored in the "room_availability" table.
 */
@Entity
@Table(name = "room_availability") // Maps the entity to the "room_availability" table.
public class RoomAvailability {

    @Id // Primary key field.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID using the database's identity strategy.
    private long id; // Unique identifier for the availability entry.

    private LocalDate availableDate; // Date for which the room availability applies.
    private int availableCount; // Number of available rooms for the specified date.
    private double price; // Price of the room for that date.

    @ManyToOne // Many availability records belong to one room.
    @JoinColumn(name = "room_id") // Foreign key referencing the room.
    private Rooms room; // The room to which this availability record belongs.

    public long getId() { // Getter for ID.
        return id;
    }

    public LocalDate getAvailableDate() { // Getter for available date.
        return availableDate;
    }

    public int getAvailableCount() { // Getter for available count.
        return availableCount;
    }

    public double getPrice() { // Getter for price.
        return price;
    }

    public Rooms getRoom() { // Getter for associated room.
        return room;
    }

    public void setId(long id) { // Setter for ID.
        this.id = id;
    }

    public void setAvailableDate(LocalDate availableDate) { // Setter for available date.
        this.availableDate = availableDate;
    }

    public void setAvailableCount(int availableCount) { // Setter for available count.
        this.availableCount = availableCount;
    }

    public void setPrice(double price) { // Setter for price.
        this.price = price;
    }

    public void setRoom(Rooms room) { // Setter for associated room.
        this.room = room;
    }
}
