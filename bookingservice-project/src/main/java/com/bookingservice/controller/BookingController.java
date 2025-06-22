package com.bookingservice.controller; // Defines the controller package for the Booking Service.

import com.bookingservice.client.PaymentClient; // Feign client to call Payment Service.
import com.bookingservice.client.PropertyClient; // Feign client to call Property Service.

import com.bookingservice.dto.*; // DTOs for API communication.
import com.bookingservice.entity.BookingDate; // Entity representing individual booked dates.
import com.bookingservice.entity.Bookings; // Entity representing a booking record.
import com.bookingservice.repository.BookingDateRepository; // Repository for BookingDate entity.
import com.bookingservice.repository.BookingRepository; // Repository for Bookings entity.

import org.springframework.beans.factory.annotation.Autowired; // Injects Spring beans.
import org.springframework.web.bind.annotation.*; // REST controller annotations.

import java.time.LocalDate; // For handling booking dates.
import java.util.ArrayList; // Used for collecting response messages.
import java.util.List; // List interface.
import java.util.Optional; // Used to safely handle null results.

/**
 * BookingController handles all endpoints related to booking operations:
 * - Adding a booking to cart
 * - Proceeding to payment
 * - Updating booking status after payment
 */
@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {

    @Autowired
    private PropertyClient propertyClient;

    @Autowired
    private PaymentClient paymentClient;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingDateRepository bookingDateRepository;

    /**
     * Adds a booking to the cart and checks room availability for the requested dates.
     * Saves booking and individual date entries, and updates room count.
     */
    @PostMapping("/add-to-cart")
    public APIResponse<List<String>> cart(@RequestBody BookingDto bookingDto) {

        Optional<RoomAvailability> matchedRoom = Optional.empty();
        APIResponse<List<String>> apiResponse = new APIResponse<>();
        List<String> messages = new ArrayList<>();

        // Fetch property, room type, and availability from Property Service
        APIResponse<PropertyDto> response = propertyClient.getPropertyById(bookingDto.getPropertyId());
        APIResponse<Rooms> roomType = propertyClient.getRoomType(bookingDto.getRoomId());
        APIResponse<List<RoomAvailability>> totalRoomsAvailable = propertyClient.getTotalRoomsAvailable(bookingDto.getRoomId());
        List<RoomAvailability> availableRooms = totalRoomsAvailable.getData();

        // Check availability for each date
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

            matchedRoom = availableRooms.stream()
                    .filter(ra -> ra.getAvailableDate().equals(date) && ra.getAvailableCount() > 0)
                    .findFirst();
        }

        // Save booking with "pending" status
        Bookings bookings = new Bookings();
        bookings.setName(bookingDto.getName());
        bookings.setEmail(bookingDto.getEmail());
        bookings.setMobile(bookingDto.getMobile());
        bookings.setPropertyName(response.getData().getName());
        bookings.setStatus("pending");
        bookings.setTotalPrice(roomType.getData().getBasePrice() * bookingDto.getTotalNigths());
        Bookings savedBooking = bookingRepository.save(bookings);

        // Save each booked date and update room availability
        for (LocalDate date : bookingDto.getDate()) {
            BookingDate bookingDate = new BookingDate();
            bookingDate.setDate(date);
            bookingDate.setBookings(savedBooking);

            BookingDate savedBookingDate = bookingDateRepository.save(bookingDate);
            if (savedBookingDate != null) {
                propertyClient.updateRoomCount(matchedRoom.get().getId(), date); // Decrease room count
            }
        }

        return null; // TODO: Return meaningful response with saved booking details if needed
    }

    /**
     * Initiates Stripe payment by forwarding product details to the Payment Service.
     */
    @PostMapping("/proceed-payment")
    public StripeResponse proceedPayment(@RequestBody ProductRequest productRequest) {
        StripeResponse stripeResponse = paymentClient.checkoutProducts(productRequest);
        return stripeResponse;
    }

    /**
     * Updates the status of a booking to "confirmed" after successful payment.
     */
    @PutMapping("/update-status-booking")
    public boolean updateBooking(@RequestParam long id) {
        Optional<Bookings> opBooking = bookingRepository.findById(id);
        if (opBooking.isPresent()) {
            Bookings bookings = opBooking.get();
            bookings.setStatus("confirmed");
            Bookings savedBooking = bookingRepository.save(bookings);
            return savedBooking != null;
        }
        return false;
    }
}
