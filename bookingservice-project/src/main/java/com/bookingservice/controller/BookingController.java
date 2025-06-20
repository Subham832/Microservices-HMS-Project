package com.bookingservice.controller;

import com.bookingservice.client.PropertyClient;
import com.bookingservice.dto.*;
import com.bookingservice.entity.BookingDate;
import com.bookingservice.entity.Bookings;
import com.bookingservice.repository.BookingDateRepository;
import com.bookingservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {


    @Autowired
    private PropertyClient propertyClient;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingDateRepository bookingDateRepository;


    @PostMapping("/add-to-cart")
    public APIResponse<List<String>> cart(@RequestBody BookingDto bookingDto) {


        APIResponse<List<String>> apiResponse = new APIResponse<>();

        List<String> messages = new ArrayList<>();

        APIResponse<PropertyDto> response = propertyClient.getPropertyById(bookingDto.getPropertyId());

        APIResponse<Rooms> roomType = propertyClient.getRoomType(bookingDto.getRoomId());

        APIResponse<List<RoomAvailability>> totalRoomsAvailable = propertyClient.getTotalRoomsAvailable(bookingDto.getRoomId());

        List<RoomAvailability> availableRooms = totalRoomsAvailable.getData();

        //Logic to check available rooms based on date and count
        for (LocalDate date : bookingDto.getDate()) {
            boolean isAvailable = availableRooms.stream()
                    .anyMatch(ra -> ra.getAvailableDate().equals(date) && ra.getAvailableCount() > 0);


            System.out.println("Date " + date + " available: " + isAvailable);

            if (!isAvailable) {
                messages.add("Room not available on: " + date);
                apiResponse.setMessage("Sold Out");
                apiResponse.setStatus(500);
                apiResponse.setData(messages);
                return apiResponse;
            }

        }
        //Save it to Booking Table with status pending
        Bookings bookings = new Bookings();
        bookings.setName(bookingDto.getName());
        bookings.setEmail(bookingDto.getEmail());
        bookings.setMobile(bookingDto.getMobile());
        bookings.setPropertyName(response.getData().getName());
        bookings.setStatus("pending");
        bookings.setTotalPrice(roomType.getData().getBasePrice() * bookingDto.getTotalNigths());
        Bookings savedBooking = bookingRepository.save(bookings);

        for (LocalDate date : bookingDto.getDate()) {
            BookingDate bookingDate = new BookingDate();
            bookingDate.setDate(date);
            bookingDate.setBookings(savedBooking);
            bookingDateRepository.save(bookingDate);
            //Call To Update Room Count Using Feign Client
        }
        return null;
    }

}