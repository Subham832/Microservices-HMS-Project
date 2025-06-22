package com.propertyservice.entity; // Defines the package for entity classes mapped to DB tables.

import com.fasterxml.jackson.annotation.JsonManagedReference; // Handles parent side of bidirectional relationship during JSON serialization.
import jakarta.persistence.*; // Imports all necessary JPA annotations.

import java.util.List; // Used to represent a list of room entities.

/**
 * Property is a JPA entity representing a property listing in the system.
 * It is mapped to the "property" table in the database and includes details such as
 * beds, rooms, bathrooms, location, and its associated rooms.
 */
@Entity
@Table(name = "property") // Maps this class to the "property" table.
public class Property {

    @Id // Marks the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Uses identity strategy for auto-incrementing ID.
    private long id; // Unique identifier for the property.

    private String name; // Name of the property.

    @Column(name = "number_of_beds") // Maps to "number_of_beds" column.
    private int numberOfBeds;

    @Column(name = "number_of_rooms") // Maps to "number_of_rooms" column.
    private int numberOfRooms;

    @Column(name = "number_of_bathrooms") // Maps to "number_of_bathrooms" column.
    private int numberOfBathrooms;

    @Column(name = "number_of_guests_allowed") // Maps to "number_of_guests_allowed" column.
    private int numberOfGuestAllowed;

    @ManyToOne // Many properties can belong to one city.
    @JoinColumn(name = "city_id") // Foreign key column in the "property" table.
    private City city;

    @ManyToOne // Many properties can belong to one area.
    @JoinColumn(name = "area_id") // Foreign key column for area.
    private Area area;

    @ManyToOne // Many properties can belong to one state.
    @JoinColumn(name = "state_id") // Foreign key column for state.
    private State state;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true) // Property has many rooms.
    @JsonManagedReference // Handles forward reference serialization.
    private List<Rooms> rooms; // List of rooms associated with this property.

    public List<Rooms> getRooms() { // Getter for rooms.
        return rooms;
    }

    public void setRooms(List<Rooms> rooms) { // Setter for rooms.
        this.rooms = rooms;
    }

    public City getCity() { // Getter for city.
        return city;
    }

    public void setCity(City city) { // Setter for city.
        this.city = city;
    }

    public Area getArea() { // Getter for area.
        return area;
    }

    public void setArea(Area area) { // Setter for area.
        this.area = area;
    }

    public State getState() { // Getter for state.
        return state;
    }

    public void setState(State state) { // Setter for state.
        this.state = state;
    }

    public long getId() { // Getter for ID.
        return id;
    }

    public void setId(long id) { // Setter for ID.
        this.id = id;
    }

    public String getName() { // Getter for property name.
        return name;
    }

    public void setName(String name) { // Setter for property name.
        this.name = name;
    }

    public int getNumberOfBeds() { // Getter for number of beds.
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) { // Setter for number of beds.
        this.numberOfBeds = numberOfBeds;
    }

    public int getNumberOfRooms() { // Getter for number of rooms.
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) { // Setter for number of rooms.
        this.numberOfRooms = numberOfRooms;
    }

    public int getNumberOfBathrooms() { // Getter for number of bathrooms.
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) { // Setter for number of bathrooms.
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public int getNumberOfGuestAllowed() { // Getter for number of guests allowed.
        return numberOfGuestAllowed;
    }

    public void setNumberOfGuestAllowed(int numberOfGuestAllowed) { // Setter for number of guests allowed.
        this.numberOfGuestAllowed = numberOfGuestAllowed;
    }
}
