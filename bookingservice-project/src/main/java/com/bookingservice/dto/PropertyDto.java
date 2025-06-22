package com.bookingservice.dto;
// Declares the package this class belongs to.

public class PropertyDto {
    // Defines a public class named PropertyDto.

    private long id;
    // Private field to store the property's unique identifier.

    private String name;
    // Private field to store the property's name.

    private int numberOfBeds;
    // Private field to store how many beds the property has.

    private int numberOfRooms;
    // Private field to store the total number of rooms.

    private int numberOfBathrooms;
    // Private field to store the number of bathrooms.

    private int numberOfGuestAllowed;
    // Private field to store the maximum number of guests allowed.

    private String city;
    // Private field to store the city where the property is located.

    private String area;
    // Private field to store the specific area or locality of the property.

    private String state;
    // Private field to store the state where the property is located.

    // Getter method for 'id' field.
    public long getId() {
        return id;
    }

    // Getter method for 'name' field.
    public String getName() {
        return name;
    }

    // Getter method for 'numberOfBeds' field.
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    // Getter method for 'numberOfRooms' field.
    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    // Getter method for 'numberOfBathrooms' field.
    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    // Getter method for 'numberOfGuestAllowed' field.
    public int getNumberOfGuestAllowed() {
        return numberOfGuestAllowed;
    }

    // Getter method for 'city' field.
    public String getCity() {
        return city;
    }

    // Getter method for 'area' field.
    public String getArea() {
        return area;
    }

    // Getter method for 'state' field.
    public String getState() {
        return state;
    }

    // Setter method for 'id' field.
    public void setId(long id) {
        this.id = id;
    }

    // Setter method for 'name' field.
    public void setName(String name) {
        this.name = name;
    }

    // Setter method for 'numberOfBeds' field.
    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    // Setter method for 'numberOfRooms' field.
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    // Setter method for 'numberOfBathrooms' field.
    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    // Setter method for 'numberOfGuestAllowed' field.
    public void setNumberOfGuestAllowed(int numberOfGuestAllowed) {
        this.numberOfGuestAllowed = numberOfGuestAllowed;
    }

    // Setter method for 'city' field.
    public void setCity(String city) {
        this.city = city;
    }

    // Setter method for 'area' field.
    public void setArea(String area) {
        this.area = area;
    }

    // Setter method for 'state' field.
    public void setState(String state) {
        this.state = state;
    }
}
