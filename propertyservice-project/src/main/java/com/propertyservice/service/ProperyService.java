package com.propertyservice.service; // Defines the service layer package for property-related business logic.


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.propertyservice.entity.*;
import com.propertyservice.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.propertyservice.controller.PropertyController;
import com.propertyservice.dto.APIResponse;
import com.propertyservice.dto.EmailRequest;
import com.propertyservice.dto.PropertyDto;
import com.propertyservice.dto.RoomsDto;

import ch.qos.logback.core.joran.util.beans.BeanUtil;


@Service // Indicates that this class is a service component containing business logic related to property management.
public class ProperyService { // Typo: Consider renaming to PropertyService for clarity and correctness.

    // Injects JPA repositories and other services used in property creation and file handling.
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
    private S3Service s3Service; // Service for uploading files to Amazon S3.
    @Autowired
    private EmailProducer emailProducer;

    @Autowired
    private PropertyPhotosRepository propertyPhotosRepository;

    /**
     * Adds a new property to the system by persisting property details, rooms, and associated photos.
     *
     * @param dto   Property details received from the client.
     * @param files List of image files to be uploaded to AWS S3.
     * @return The saved Property entity.
     */
    public Property addProperty(PropertyDto dto, MultipartFile[] files) {

        // Fetch the city, area, and state entities using their respective names for reference integrity.
        String cityName = dto.getCity();
        City city = cityRepository.findByName(cityName);

        String areaName = dto.getArea();
        Area area = areaRepository.findByName(areaName);

        String stateName = dto.getState();
        State state = stateRepository.findByName(stateName);

        // Create a new Property entity and populate it with data from the DTO.
        Property property = new Property();
        property.setName(dto.getName());
        property.setNumberOfBathrooms(dto.getNumberOfBathrooms());
        property.setNumberOfBeds(dto.getNumberOfBeds());
        property.setNumberOfRooms(dto.getNumberOfRooms());
        property.setNumberOfGuestAllowed(dto.getNumberOfGuestAllowed());
        property.setArea(area);
        property.setCity(city);
        property.setState(state);

        // Save the populated property entity to the database.
        Property savedProperty = propertyRepository.save(property);

        // Persist associated room details for the saved property.
        for (RoomsDto roomsDto : dto.getRooms()) {
            Rooms rooms = new Rooms();
            rooms.setProperty(savedProperty);
            rooms.setRoomType(roomsDto.getRoomType());
            rooms.setBasePrice(roomsDto.getBasePrice());
            roomRepository.save(rooms);
        }

        // Upload property images to Amazon S3 and capture the returned URLs.
        List<String> fileUrls = s3Service.uploadFiles(files);

        // Save uploaded image URLs as PropertyPhotos entities linked to the saved property.
        for (String url : fileUrls) {
            PropertyPhotos photo = new PropertyPhotos();
            photo.setUrl(url);
            photo.setProperty(savedProperty);
            propertyPhotosRepository.save(photo);
        }
        emailProducer.sendEmail(new EmailRequest(
                "mrsubhamkumar001@gmail.com",
                "Property added!",
                "Your property has been successfully added."
        ));
        // Return the fully constructed and saved Property entity.
        return savedProperty;
    }
}
