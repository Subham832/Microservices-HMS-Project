package com.propertyservice.entity; // Defines the package for JPA entity classes.

import jakarta.persistence.Column; // Allows customization of the column name.
import jakarta.persistence.Entity; // Marks this class as a JPA entity.
import jakarta.persistence.GeneratedValue; // Marks the field for auto generation.
import jakarta.persistence.GenerationType; // Strategy for primary key generation.
import jakarta.persistence.Id; // Declares the primary key field.
import jakarta.persistence.JoinColumn; // Specifies the foreign key column.
import jakarta.persistence.ManyToOne; // Defines many-to-one relationship.
import jakarta.persistence.Table; // Maps this class to a database table.
import com.fasterxml.jackson.annotation.JsonBackReference; // Prevents infinite recursion during JSON serialization.

/**
 * Rooms is a JPA entity representing a room within a property.
 * It includes room type, base price, and its associated property.
 */
@Entity
@Table(name = "rooms") // Maps this entity to the "rooms" table in the database.
public class Rooms {

    @Id // Primary key for the room entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Uses identity column for auto-increment.
    private long id; // Unique identifier for the room.

    private String roomType; // Type of the room (e.g., Deluxe, Standard).

    @Column(name = "base_price") // Maps to the "base_price" column in DB.
    private double basePrice; // Base price for the room.

    @ManyToOne // Many rooms belong to one property.
    @JoinColumn(name = "property_id") // Foreign key to the property table.
    @JsonBackReference // Prevents infinite loop when serializing bidirectional relationships.
    private Property property; // Reference to the property this room belongs to.

    public long getId() { // Getter for room ID.
        return id;
    }

    public String getRoomType() { // Getter for room type.
        return roomType;
    }

    public double getBasePrice() { // Getter for base price.
        return basePrice;
    }

    public Property getProperty() { // Getter for associated property.
        return property;
    }

    public void setId(long id) { // Setter for room ID.
        this.id = id;
    }

    public void setRoomType(String roomType) { // Setter for room type.
        this.roomType = roomType;
    }

    public void setBasePrice(double basePrice) { // Setter for base price.
        this.basePrice = basePrice;
    }

    public void setProperty(Property property) { // Setter for associated property.
        this.property = property;
    }
}
