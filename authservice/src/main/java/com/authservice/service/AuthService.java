package com.authservice.service;

import com.authservice.dto.APIResponse;
import com.authservice.dto.UserDto;
import com.authservice.entity.User;
import com.authservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for authentication-related operations.
 */
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registers a new user based on the provided UserDto.
     *
     * @param userDto DTO containing user registration data
     * @return APIResponse containing registration result
     */
    public APIResponse<String> register(UserDto userDto) {

        // API Response Object
        APIResponse<String> response = new APIResponse<>();

        // Check whether username exists
        if (userRepository.existsByUsername(userDto.getUsername())) {
            response.setMessage("Registration Failed");
            response.setStatus(500);
            response.setData("User with name already exists");
            return response;
        }

        // Check whether email exists
        if (userRepository.existsByEmail(userDto.getEmail())) {
            response.setMessage("Registration Failed");
            response.setStatus(500);
            response.setData("User with email already exists");
            return response;
        }

        // Encode the password before saving it to the database
        String encryptedPassword = passwordEncoder.encode(userDto.getPassword());

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        user.setPassword(encryptedPassword);
        user.setRole("ROLE_ADMIN");

        User savedUser = userRepository.save(user);

        if (savedUser == null) {
            // Custom exception can be thrown here (e.g., RegistrationFailedException)
        }

        response.setMessage("Registration Completed");
        response.setStatus(201);
        response.setData("User has been registered");
        return response;

        // Finally save the user and return response as APIResponse
    }
}
