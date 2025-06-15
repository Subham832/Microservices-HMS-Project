package com.authservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for exposing welcome-related endpoints.
 *
 * <p>This endpoint can be used for service health check or
 * initial handshake testing in the Authentication Service module.</p>
 */
@RestController
@RequestMapping("/api/v1/welcome")
public class WelcomeController {

    /**
     * Returns a static welcome message.
     *
     * @return welcome message string
     */
    @GetMapping("/message") // Endpoint: GET http://localhost:8086/api/v1/welcome/message
    public String welcome() {
        return "Welcome!";
    }
}
