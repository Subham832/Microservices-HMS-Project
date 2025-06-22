package com.bookingservice.dto; // DTO package for Booking Service.

import java.time.LocalDate; // Represents booking dates.
import java.util.List; // List of dates or other data.

/**
 * BookingDto is used to transfer booking request data from client to backend.
 * It includes property and room IDs, guest details, pricing, status, and date range.
 */
public class BookingDto {

    private long propertyId; // ID of the selected property.
    private long roomId; // ID of the selected room type.
    private String name; // Guest name.
    private String email; // Guest email.
    private String mobile; // Guest mobile number.
    private String status; // Booking status (e.g., pending, confirmed).
    private double price; // Base price per night.
    private int totalNigths; // Total number of nights booked.
    private double totalPrice; // Total calculated price.
    private List<LocalDate> date; // List of dates booked.

    public double getPrice() {
        return price;
    }

    public int getTotalNigths() {
        return totalNigths;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotalNigths(int totalNigths) {
        this.totalNigths = totalNigths;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public long getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public List<LocalDate> getDate() {
        return date;
    }

    public void setDate(List<LocalDate> date) {
        this.date = date;
    }
}
