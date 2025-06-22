package com.bookingservice.client; // Defines the Feign client interface package for communicating with external services.

import com.bookingservice.dto.APIResponse; // Standard API response wrapper.
import com.bookingservice.dto.PropertyDto; // DTO containing property data.
import com.bookingservice.dto.RoomAvailability; // DTO for room availability info.

import org.springframework.cloud.openfeign.FeignClient; // Enables declarative REST clients.
import org.springframework.web.bind.annotation.GetMapping; // Maps HTTP GET requests.
import org.springframework.web.bind.annotation.PutMapping; // Maps HTTP PUT requests.
import org.springframework.web.bind.annotation.RequestParam; // Maps query parameters to method parameters.

import java.time.LocalDate; // Represents a date without time zone.
import java.util.List; // List interface for returning multiple records.

/**
 * PropertyClient is a Feign client interface used to communicate with the Property Service.
 * It fetches property, room, and availability details, and updates room count post-booking.
 */
@FeignClient(name = "PROPERTYSERVICE") // Connects to the Property Service via Eureka.
public interface PropertyClient {

    @GetMapping("/api/v1/property/property-id") // Retrieves property by ID.
    APIResponse<PropertyDto> getPropertyById(@RequestParam long id);

    @GetMapping("/api/v1/property/room-available-room-id") // Retrieves room availability by room ID.
    APIResponse<List<RoomAvailability>> getTotalRoomsAvailable(@RequestParam long id);

    @GetMapping("/api/v1/property/room-id") // Gets room type by ID.
    APIResponse<com.bookingservice.dto.Rooms> getRoomType(@RequestParam long id);

    @PutMapping("/api/v1/property/updateRoomCount") // Updates room count after booking.
    APIResponse<Boolean> updateRoomCount(@RequestParam long id, @RequestParam LocalDate date);
}
