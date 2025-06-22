package com.propertyservice.entity; // Defines the package for JPA entity classes.

import jakarta.persistence.Entity; // Marks this class as a JPA entity.
import jakarta.persistence.GeneratedValue; // Specifies that the ID is auto-generated.
import jakarta.persistence.GenerationType; // Strategy used for ID generation.
import jakarta.persistence.Id; // Identifies the primary key field.
import jakarta.persistence.Table; // Maps the class to a specific database table.

/**
 * State is a JPA entity representing a state/region.
 * It is mapped to the "state" table in the database and holds the state name.
 */
@Entity
@Table(name = "state") // Maps this entity to the "state" table.
public class State {

    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Uses identity strategy for auto-incremented ID.
    private long id; // Unique identifier for the state.

    private String name; // Name of the state.

    public long getId() { // Getter for ID.
        return id;
    }

    public void setId(long id) { // Setter for ID.
        this.id = id;
    }

    public String getName() { // Getter for state name.
        return name;
    }

    public void setName(String name) { // Setter for state name.
        this.name = name;
    }
}
