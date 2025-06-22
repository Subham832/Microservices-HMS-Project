package com.propertyservice.entity; // Defines the package location for JPA entity classes.

import jakarta.persistence.Entity; // Specifies that the class is an entity mapped to a database table.
import jakarta.persistence.GeneratedValue; // Marks the field for auto-generation.
import jakarta.persistence.GenerationType; // Specifies the primary key generation strategy.
import jakarta.persistence.Id; // Marks the primary key of the entity.
import jakarta.persistence.Table; // Maps the entity to a specific table in the database.

/**
 * Area is a JPA entity representing an area/locality.
 * It is mapped to the "area" table in the database.
 */
@Entity
@Table(name = "area") // Maps this entity to the "area" table.
public class Area {

    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID using the database identity column.
    private long id; // Unique identifier for the area.

    private String name; // Name of the area/locality.

    public long getId() { // Getter for ID.
        return id;
    }

    public void setId(long id) { // Setter for ID.
        this.id = id;
    }

    public String getName() { // Getter for area name.
        return name;
    }

    public void setName(String name) { // Setter for area name.
        this.name = name;
    }
}
