package com.propertyservice.entity; // Defines the package location for JPA entity classes.

import jakarta.persistence.Entity; // Marks this class as a JPA entity.
import jakarta.persistence.GeneratedValue; // Specifies automatic value generation for the primary key.
import jakarta.persistence.GenerationType; // Defines the strategy for primary key generation.
import jakarta.persistence.Id; // Indicates the primary key field.
import jakarta.persistence.Table; // Maps the entity to a specific database table.

/**
 * City is a JPA entity representing a city.
 * It is mapped to the "city" table in the database.
 */
@Entity
@Table(name = "city") // Maps this entity to the "city" table.
public class City {

    @Id // Primary key of the entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Uses identity column strategy for ID generation.
    private long id; // Unique identifier for the city.

    private String name; // Name of the city.

    public long getId() { // Getter for ID.
        return id;
    }

    public void setId(long id) { // Setter for ID.
        this.id = id;
    }

    public String getName() { // Getter for city name.
        return name;
    }

    public void setName(String name) { // Setter for city name.
        this.name = name;
    }
}
