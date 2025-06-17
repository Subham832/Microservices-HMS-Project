package com.authservice.controller;

import com.authservice.dto.APIResponse;
import com.authservice.dto.LoginDto;
import com.authservice.dto.UserDto;
import com.authservice.entity.User;
import com.authservice.repository.UserRepository;
import com.authservice.service.AuthService;
import com.authservice.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for authentication operations.
 * <p>
 * Handles user registration and login endpoints.
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserRepository userRepository;

    /**
     * Registers a new user in the system.
     * <p>
     * Endpoint: POST http://localhost:8086/api/v1/auth/register
     *
     * @param dto DTO containing user registration details
     * @return APIResponse with registration result
     */
    @PostMapping("/register")
    public ResponseEntity<APIResponse<String>> register(@RequestBody UserDto dto) {
        APIResponse<String> response = authService.register(dto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

    /**
     * Authenticates user and generates JWT token on success.
     * <p>
     * Endpoint: POST http://localhost:8086/api/v1/auth/login
     * <p>
     * Workflow:
     * - Wraps credentials in UsernamePasswordAuthenticationToken
     * - Attempts authentication via AuthenticationManager
     * - If successful, generates JWT token with role claim
     * - Returns token as part of APIResponse
     *
     * @param dto DTO containing login credentials
     * @return APIResponse with JWT token or error message
     */
    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> login(@RequestBody LoginDto dto) {
        APIResponse<String> response = new APIResponse<>();

        // Wrap credentials into authentication token
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

        try {
            // Attempt authentication
            Authentication authenticate = authManager.authenticate(token);

            // If authentication successful
            if (authenticate.isAuthenticated()) {
                String jwtToken = jwtService.generateToken(
                        dto.getUsername(),
                        authenticate.getAuthorities().iterator().next().getAuthority()
                );

                response.setMessage("Login Successful");
                response.setStatus(200);
                response.setData(jwtToken);
                return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
            }

        } catch (Exception e) {
            // Log the exception (logging framework should be used in real-world apps)
            e.printStackTrace();
        }

        // If authentication fails
        response.setMessage("Failed");
        response.setStatus(401);
        response.setData("Un-Authorized Access");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

    @GetMapping("/get-user")
    public User getUserByUserName(@RequestParam String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }
}
