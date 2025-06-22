package com.propertyservice.controller; // Defines the package location for controller classes in the Property Service module.

import com.fasterxml.jackson.core.JsonProcessingException; // Handles errors that may occur during JSON processing.
import com.fasterxml.jackson.databind.ObjectMapper; // Used to convert JSON strings to Java objects and vice versa.
import com.propertyservice.dto.APIResponse; // Custom API response wrapper for standardizing response format.
import com.propertyservice.dto.PropertyDto; // Data Transfer Object for property information.
import com.propertyservice.entity.Property; // Entity class representing a Property.
import com.propertyservice.entity.RoomAvailability; // Entity class representing room availability for a property.
import com.propertyservice.entity.Rooms; // Entity class representing types of rooms.
import com.propertyservice.repository.RoomAvailabilityRepository; // Repository interface for accessing room availability data.
import com.propertyservice.service.PropertyService; // Service interface that contains business logic for property operations.
import org.springframework.beans.factory.annotation.Autowired; // Enables automatic dependency injection.
import org.springframework.format.annotation.DateTimeFormat; // Specifies date format for request parameters.
import org.springframework.http.HttpStatus; // Represents HTTP status codes.
import org.springframework.http.MediaType; // Provides media type constants for content negotiation.
import org.springframework.http.ResponseEntity; // Represents HTTP response including status and body.
import org.springframework.web.bind.annotation.*; // Includes all necessary annotations for building REST controllers.
import org.springframework.web.multipart.MultipartFile; // Represents uploaded file received in a multipart request.

import java.time.LocalDate; // Represents a date (without time zone).
import java.util.List; // Interface for list collections.

@RestController // Marks this class as a REST controller, automatically handling JSON response conversion.
@RequestMapping("/api/v1/property") // Base path for all endpoints in this controller.
public class PropertyController {

    @Autowired // Injects PropertyService bean.
    private PropertyService propertyService;

    @Autowired // Injects RoomAvailabilityRepository bean.
    private RoomAvailabilityRepository roomAvailabilityRepository;

    @PostMapping(
            value = "/add-property",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, // Accepts multipart form data (for file upload).
            produces = MediaType.APPLICATION_JSON_VALUE // Responds with JSON format.
    )
    public ResponseEntity<APIResponse> addProperty(
            @RequestParam("property") String propertyJson, // Accepts property data as raw JSON string.
            @RequestParam("files") MultipartFile[] files) { // Accepts uploaded files (images, etc.)

        ObjectMapper objectMapper = new ObjectMapper(); // JSON parser for converting string to DTO.
        PropertyDto dto = null;
        try {
            dto = objectMapper.readValue(propertyJson, PropertyDto.class); // Converts JSON string to PropertyDto.
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Returns 400 if JSON parsing fails.
        }

        Property property = propertyService.addProperty(dto, files); // Calls service method to save property and files.

        APIResponse<Property> response = new APIResponse<>(); // Constructs API response.
        response.setMessage("Property added");
        response.setStatus(201);
        response.setData(property);

        return new ResponseEntity<>(response, HttpStatus.CREATED); // Returns 201 Created with response.
    }

    @GetMapping("/search-property") // Endpoint to search properties by name and date.
    public APIResponse searchProperty(
            @RequestParam String name,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
        APIResponse response = propertyService.searchProperty(name, date); // Delegates search logic to service.
        return response;
    }

    @GetMapping("/property-id") // Endpoint to fetch a property by its ID.
    public APIResponse<PropertyDto> getPropertyById(@RequestParam long id) {
        APIResponse<PropertyDto> response = propertyService.findPropertyById(id); // Fetches property DTO by ID.
        return response;
    }

    @GetMapping("/room-available-room-id") // Endpoint to fetch available rooms by property ID.
    public APIResponse<List<RoomAvailability>> getTotalRoomsAvailable(@RequestParam long id) {
        List<RoomAvailability> totalRooms = propertyService.getTotalRoomsAvailable(id); // Gets room availability list.

        APIResponse<List<RoomAvailability>> response = new APIResponse<>(); // Constructs API response.
        response.setMessage("Total rooms");
        response.setStatus(200);
        response.setData(totalRooms);
        return response;
    }

    @GetMapping("/room-id") // Endpoint to get room type by room ID.
    public APIResponse<Rooms> getRoomType(@RequestParam long id) {
        Rooms room = propertyService.getRoomById(id); // Gets room details from service.

        APIResponse<Rooms> response = new APIResponse<>(); // Constructs API response.
        response.setMessage("Total rooms");
        response.setStatus(200);
        response.setData(room);
        return response;
    }

    @PutMapping("/updateRoomCount") // Endpoint to update room count for a given ID and date.
    public APIResponse<Boolean> updateRoomCount(@RequestParam long id, @RequestParam LocalDate date) {
        APIResponse<Boolean> response = new APIResponse<>();
        RoomAvailability roomsAvailable = roomAvailabilityRepository.getRooms(id, date); // Fetches room availability.

        int count = roomsAvailable.getAvailableCount(); // Gets current available count.

        if (count > 0) {
            roomsAvailable.setAvailableCount(count - 1); // Decreases available count by 1.
            roomAvailabilityRepository.save(roomsAvailable); // Saves updated entity.
            response.setMessage("Updated");
            response.setStatus(200);
            response.setData(true);
            return response;
        } else {
            response.setMessage("No Availability"); // If no rooms available, return failure message.
            response.setStatus(500);
            response.setData(false);
            return response;
        }
    }
}
