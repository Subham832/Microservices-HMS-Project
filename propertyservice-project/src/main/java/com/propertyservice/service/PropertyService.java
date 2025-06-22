package com.propertyservice.service; // Defines the service layer package for property-related business logic.

import com.propertyservice.constants.AppConstants; // Application constants (e.g., Kafka topic name).
import com.propertyservice.dto.APIResponse; // Standardized API response wrapper.
import com.propertyservice.dto.EmailRequest; // DTO for sending email messages through Kafka.
import com.propertyservice.dto.PropertyDto; // DTO containing property information from the client.
import com.propertyservice.dto.RoomsDto; // DTO representing room details.

import com.propertyservice.entity.*; // Imports all entity classes.
import com.propertyservice.repository.*; // Imports all repository interfaces.

import org.springframework.beans.BeanUtils; // Used for copying property values from one object to another.
import org.springframework.beans.factory.annotation.Autowired; // Injects Spring beans.
import org.springframework.kafka.core.KafkaTemplate; // Used to publish messages to Kafka.
import org.springframework.stereotype.Service; // Marks the class as a service component.
import org.springframework.web.multipart.MultipartFile; // Represents uploaded files.

import java.time.LocalDate; // Date-only value for room availability.
import java.util.ArrayList; // Used to create a list of room DTOs.
import java.util.List; // Interface for list data structures.
import java.util.Optional; // Represents optional values to handle nulls safely.

/**
 * PropertyService handles the business logic related to managing properties,
 * including adding new properties, searching, and retrieving property/room details.
 */
@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomAvailabilityRepository availabilityRepository;

    @Autowired
    private S3Service s3Service; // Service to handle S3 file uploads.

    @Autowired
    private PropertyPhotosRepository propertyPhotosRepository;

    @Autowired
    private KafkaTemplate<String, EmailRequest> kafkaTemplate; // Kafka producer to send email notifications.

    /**
     * Adds a new property along with its rooms and sends an email notification via Kafka.
     *
     * @param dto   The DTO containing property details.
     * @param files The image files to be uploaded to AWS S3 (currently commented out).
     * @return The saved Property entity.
     */
    public Property addProperty(PropertyDto dto, MultipartFile[] files) {

        String cityName = dto.getCity();
        City city = cityRepository.findByName(cityName);

        String areaName = dto.getArea();
        Area area = areaRepository.findByName(areaName);

        String stateName = dto.getState();
        State state = stateRepository.findByName(stateName);

        Property property = new Property();
        property.setName(dto.getName());
        property.setNumberOfBathrooms(dto.getNumberOfBathrooms());
        property.setNumberOfBeds(dto.getNumberOfBeds());
        property.setNumberOfRooms(dto.getNumberOfRooms());
        property.setNumberOfGuestAllowed(dto.getNumberOfGuestAllowed());
        property.setArea(area);
        property.setCity(city);
        property.setState(state);

        Property savedProperty = propertyRepository.save(property); // Persist property

        // Persist rooms
        for (RoomsDto roomsDto : dto.getRooms()) {
            Rooms rooms = new Rooms();
            rooms.setProperty(savedProperty);
            rooms.setRoomType(roomsDto.getRoomType());
            rooms.setBasePrice(roomsDto.getBasePrice());
            roomRepository.save(rooms);
        }

        // Send notification email via Kafka
        EmailRequest request = new EmailRequest("mrsubhamkumar001@gmail.com", "Property Added", "Your Property Details Are Now Live");
        kafkaTemplate.send(AppConstants.TOPIC, request);

        // S3 upload logic (commented out for now)
        // List<String> fileUrls = s3Service.uploadFiles(files);
        // for (String url : fileUrls) {
        //     PropertyPhotos photo = new PropertyPhotos();
        //     photo.setUrl(url);
        //     photo.setProperty(savedProperty);
        //     propertyPhotosRepository.save(photo);
        // }

        return savedProperty;
    }

    /**
     * Searches for properties based on city/area/state name and availability date.
     */
    public APIResponse searchProperty(String name, LocalDate date) {
        List<Property> properties = propertyRepository.searchProperty(name, date);
        APIResponse<List<Property>> response = new APIResponse<>();
        response.setMessage("Search result");
        response.setStatus(200);
        response.setData(properties);
        return response;
    }

    /**
     * Finds a property by its ID and converts it to a PropertyDto.
     */
    public APIResponse<PropertyDto> findPropertyById(long id) {
        APIResponse<PropertyDto> response = new APIResponse<>();
        PropertyDto dto = new PropertyDto();

        Optional<Property> opProp = propertyRepository.findById(id);
        if (opProp.isPresent()) {
            Property property = opProp.get();
            dto.setArea(property.getArea().getName());
            dto.setCity(property.getCity().getName());
            dto.setState(property.getState().getName());

            List<Rooms> rooms = property.getRooms();
            List<RoomsDto> roomsDto = new ArrayList<>();
            for (Rooms room : rooms) {
                RoomsDto roomDto = new RoomsDto();
                BeanUtils.copyProperties(room, roomDto);
                roomsDto.add(roomDto);
            }

            dto.setRooms(roomsDto);
            BeanUtils.copyProperties(property, dto); // Copy remaining fields
            response.setMessage("Matching Record");
            response.setStatus(200);
            response.setData(dto);
            return response;
        }

        return null; // Could return 404-style response for better API design
    }

    /**
     * Retrieves room availability by room ID.
     */
    public List<RoomAvailability> getTotalRoomsAvailable(long id) {
        return availabilityRepository.findByRoomId(id);
    }

    /**
     * Retrieves room details by room ID.
     */
    public Rooms getRoomById(long id) {
        return roomRepository.findById(id).get(); // Assumes the ID exists; could use Optional for safety
    }
}
