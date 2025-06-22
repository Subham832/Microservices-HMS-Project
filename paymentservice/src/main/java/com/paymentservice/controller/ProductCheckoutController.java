package com.paymentservice.controller; // Defines the controller package for handling payment-related endpoints.

import com.paymentservice.client.BookingClient; // Feign client for communicating with the Booking Service.
import com.paymentservice.dto.ProductRequest; // DTO representing the Stripe product details.
import com.paymentservice.dto.StripeResponse; // DTO representing the response from Stripe.
import com.paymentservice.service.StripeService; // Service layer for interacting with Stripe.

import com.stripe.Stripe; // Stripe API client.
import com.stripe.exception.StripeException; // Exception thrown by Stripe SDK.
import com.stripe.model.checkout.Session; // Stripe Checkout session object.

import org.springframework.beans.factory.annotation.Autowired; // Enables dependency injection.
import org.springframework.web.bind.annotation.*; // Contains Spring Web annotations.

/**
 * ProductCheckoutController handles payment initiation, Stripe webhook redirects (success/cancel),
 * and communicates with the Booking Service to confirm bookings after successful payments.
 */
@RestController
@RequestMapping("/product/v1") // Base URL mapping for payment-related endpoints.
public class ProductCheckoutController {

    private StripeService stripeService;

    @Autowired
    private BookingClient bookingClient; // Injects the Booking Service client.

    public ProductCheckoutController(StripeService stripeService) { // Constructor injection for StripeService.
        this.stripeService = stripeService;
    }

    /**
     * Initiates Stripe checkout session based on product details.
     */
    @PostMapping("/checkout")
    public StripeResponse checkoutProducts(@RequestBody ProductRequest productRequest) {
        StripeResponse stripeResponse = stripeService.checkoutProducts(productRequest);
        return stripeResponse;
    }

    /**
     * Handles redirect from Stripe after successful payment.
     * Verifies payment status, updates booking via Booking Service, and sends confirmation if applicable.
     */
    @GetMapping("/success")
    public String handleSuccess(@RequestParam("session_id") String sessionId, @RequestParam("booking_id") long id) {
        Stripe.apiKey = ""; // TODO: Replace with actual Stripe secret key

        try {
            Session session = Session.retrieve(sessionId); // Fetch session details from Stripe
            String paymentStatus = session.getPaymentStatus();
            System.out.println(sessionId);

            if ("paid".equalsIgnoreCase(paymentStatus)) {
                System.out.println("✅ Payment successful: true");

                // Call Booking Service to update status
                boolean result = bookingClient.updateBooking(id);

                if (result) {
                    // TODO: Trigger confirmation email here
                }
                return "Payment successful";
            } else {
                System.out.println("❌ Payment not completed: false");
                return "Payment not completed";
            }

        } catch (StripeException e) {
            e.printStackTrace();
            return "Stripe error occurred";
        }
    }

    /**
     * Handles redirect from Stripe if user cancels the payment process.
     */
    @GetMapping("/cancel")
    public String handleCancel() {
        System.out.println("❌ Payment cancelled: false");
        return "Payment cancelled";
    }
}
