package com.propertyservice.entity; // Defines the package for entity classes in the application.

import jakarta.persistence.Entity; // Marks this class as a JPA entity.
import jakarta.persistence.GeneratedValue; // Indicates that the value will be generated.
import jakarta.persistence.GenerationType; // Defines the generation strategy.
import jakarta.persistence.Id; // Marks the field as the primary key.
import jakarta.persistence.JoinColumn; // Defines the foreign key column.
import jakarta.persistence.ManyToOne; // Specifies many-to-one relationship with Property.

/**
 * PropertyPhotos is a JPA entity representing a photo linked to a property.
 * Each photo has a URL and a reference to its parent property.
 */
@Entity
public class PropertyPhotos {

    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Uses identity strategy for primary key generation.
    private long id; // Unique identifier for the photo record.

    private String url; // URL where the photo is stored.

    @ManyToOne // Many photos can be associated with one property.
    @JoinColumn(name = "property_id") // Foreign key referencing the property ID.
    private Property property; // The property to which this photo belongs.

    public long getId() { // Getter for ID.
        return id;
    }

    public void setId(long id) { // Setter for ID.
        this.id = id;
    }

    public String getUrl() { // Getter for photo URL.
        return url;
    }

    public void setUrl(String url) { // Setter for photo URL.
        this.url = url;
    }

    public Property getProperty() { // Getter for associated property.
        return property;
    }

    public void setProperty(Property property) { // Setter for associated property.
        this.property = property;
    }
}
