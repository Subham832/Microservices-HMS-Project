package com.paymentservice.service; // Defines the service package for Stripe payment handling.

import com.paymentservice.dto.ProductRequest; // DTO containing product and booking details.
import com.paymentservice.dto.StripeResponse; // DTO to return session info to the frontend.

import com.stripe.Stripe; // Stripe API client.
import com.stripe.exception.StripeException; // Exception thrown by Stripe API.
import com.stripe.model.checkout.Session; // Represents a Stripe Checkout session.
import com.stripe.param.checkout.SessionCreateParams; // Parameters for creating a Checkout session.

import org.springframework.beans.factory.annotation.Value; // Injects properties from config.
import org.springframework.stereotype.Service; // Marks this class as a Spring service bean.

/**
 * StripeService handles the creation of Stripe Checkout sessions for payment processing.
 * It builds a session from product details and returns session info for client-side redirection.
 */
@Service
public class StripeService {

    @Value("${stripe.secretKey}") // Loads Stripe secret key from application.properties
    private String secretKey;

    /**
     * Creates a Stripe checkout session using product details.
     *
     * @param productRequest Object containing amount, quantity, product name, currency, and booking ID.
     * @return StripeResponse containing session ID and URL.
     */
    public StripeResponse checkoutProducts(ProductRequest productRequest) {

        long bookingId = productRequest.getBookingId();

        // Set API key to authenticate with Stripe
        Stripe.apiKey = secretKey;

        // Build product data (product name)
        SessionCreateParams.LineItem.PriceData.ProductData productData =
                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(productRequest.getName())
                        .build();

        // Build price data (amount, currency, product)
        SessionCreateParams.LineItem.PriceData priceData =
                SessionCreateParams.LineItem.PriceData.builder()
                        .setCurrency(productRequest.getCurrency() != null ? productRequest.getCurrency() : "USD")
                        .setUnitAmount(productRequest.getAmount())
                        .setProductData(productData)
                        .build();

        // Build line item (quantity, price)
        SessionCreateParams.LineItem lineItem =
                SessionCreateParams.LineItem.builder()
                        .setQuantity(productRequest.getQuantity())
                        .setPriceData(priceData)
                        .build();

        // Create session with redirect URLs and line item
        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("http://localhost:8080/product/v1/success?session_id={CHECKOUT_SESSION_ID}&booking_id=" + bookingId)
                        .setCancelUrl("http://localhost:8080/cancel")
                        .addLineItem(lineItem)
                        .build();

        Session session = null;
        try {
            session = Session.create(params); // Create the Stripe Checkout session
        } catch (StripeException e) {
            // TODO: Add proper logging
        }

        // Return session details to client
        return StripeResponse.builder()
                .status("SUCCESS")
                .message("Payment session created ")
                .sessionId(session.getId())
                .sessionUrl(session.getUrl())
                .build();
    }
}
