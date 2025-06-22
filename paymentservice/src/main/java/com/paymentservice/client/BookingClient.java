package com.paymentservice.client; // Defines the package for Feign clients in the Payment Service.

import org.springframework.cloud.openfeign.FeignClient; // Enables declarative REST client functionality.
import org.springframework.web.bind.annotation.PutMapping; // Maps HTTP PUT requests.
import org.springframework.web.bind.annotation.RequestParam; // Binds query parameters to method arguments.

/**
 * BookingClient is a Feign client used to communicate with the Booking Service.
 * It updates the booking status after a successful payment.
 */
@FeignClient("BOOKINGSERVICE-PROJECT") // Registers this interface as a Feign client targeting the Booking Service.
public interface BookingClient {

    @PutMapping("/api/v1/booking/update-status-booking") // Maps to the endpoint in BookingService to update booking status.
    boolean updateBooking(@RequestParam long id); // Sends the booking ID as a query parameter to update status.
}
