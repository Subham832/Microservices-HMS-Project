package com.propertyservice.dto; // Defines the package location for Data Transfer Objects (DTOs).

import java.util.List; // Imports List interface to handle collections of data.

/**
 * PropertyDto is a Data Transfer Object used to carry property data between layers.
 * It includes details like location, capacity, room info, and image URLs.
 */
public class PropertyDto {

    private long id; // Unique identifier for the property.
    private String name; // Name of the property.
    private int numberOfBeds; // Total number of beds in the property.
    private int numberOfRooms; // Total number of rooms in the property.
    private int numberOfBathrooms; // Total number of bathrooms.
    private int numberOfGuestAllowed; // Maximum number of guests allowed.
    private String city; // City where the property is located.
    private String area; // Area or locality within the city.
    private String state; // State where the property is located.

    private List<RoomsDto> rooms; // List of room details.
    private List<String> imageUrls; // List of image URLs associated with the property.

    public long getId() { // Getter for ID.
        return id;
    }

    public String getName() { // Getter for property name.
        return name;
    }

    public int getNumberOfBeds() { // Getter for bed count.
        return numberOfBeds;
    }

    public int getNumberOfRooms() { // Getter for room count.
        return numberOfRooms;
    }

    public int getNumberOfBathrooms() { // Getter for bathroom count.
        return numberOfBathrooms;
    }

    public int getNumberOfGuestAllowed() { // Getter for allowed guest count.
        return numberOfGuestAllowed;
    }

    public String getCity() { // Getter for city.
        return city;
    }

    public String getArea() { // Getter for area/locality.
        return area;
    }

    public String getState() { // Getter for state.
        return state;
    }

    public List<RoomsDto> getRooms() { // Getter for rooms list.
        return rooms;
    }

    public void setId(long id) { // Setter for ID.
        this.id = id;
    }

    public void setName(String name) { // Setter for property name.
        this.name = name;
    }

    public void setNumberOfBeds(int numberOfBeds) { // Setter for bed count.
        this.numberOfBeds = numberOfBeds;
    }

    public void setNumberOfRooms(int numberOfRooms) { // Setter for room count.
        this.numberOfRooms = numberOfRooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) { // Setter for bathroom count.
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public void setNumberOfGuestAllowed(int numberOfGuestAllowed) { // Setter for guest count.
        this.numberOfGuestAllowed = numberOfGuestAllowed;
    }

    public void setCity(String city) { // Setter for city.
        this.city = city;
    }

    public void setArea(String area) { // Setter for area/locality.
        this.area = area;
    }

    public void setState(String state) { // Setter for state.
        this.state = state;
    }

    public void setRooms(List<RoomsDto> rooms) { // Setter for room list.
        this.rooms = rooms;
    }

    public List<String> getImageUrls() { // Getter for image URLs.
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) { // Setter for image URLs.
        this.imageUrls = imageUrls;
    }
}
