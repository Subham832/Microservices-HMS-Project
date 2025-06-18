package com.propertyservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.propertyservice.controller.PropertyController;
import com.propertyservice.dto.APIResponse;
import com.propertyservice.dto.PropertyDto;
import com.propertyservice.dto.RoomsDto;
import com.propertyservice.entity.Area;
import com.propertyservice.entity.City;
import com.propertyservice.entity.Property;
import com.propertyservice.entity.RoomAvailability;
import com.propertyservice.entity.Rooms;
import com.propertyservice.entity.State;
import com.propertyservice.repository.AreaRepository;
import com.propertyservice.repository.CityRepository;
import com.propertyservice.repository.PropertyRepository;
import com.propertyservice.repository.RoomAvailabilityRepository;
import com.propertyservice.repository.RoomRepository;
import com.propertyservice.repository.StateRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;


@Service
public class ProperyService {

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

        Property savedProperty = propertyRepository.save(property);


        return savedProperty;
    }
}
