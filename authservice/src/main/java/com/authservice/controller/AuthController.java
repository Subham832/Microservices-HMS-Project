package com.authservice.controller;


import com.authservice.dto.APIResponse;
import com.authservice.dto.LoginDto;
import com.authservice.dto.UserDto;
import com.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private AuthenticationManager authManager;

    //http://localhost:8086/api/v1/auth/register
    @PostMapping("/register")
    public ResponseEntity<APIResponse<String>> register(@RequestBody UserDto dto) {
        APIResponse<String> response = authService.register(dto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }

    //http://localhost:8086/api/v1/auth/login
    @PostMapping("/login")
    public ResponseEntity<APIResponse<String>> login(@RequestBody LoginDto dto) {
        APIResponse<String> response = new APIResponse<>();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        try {
            Authentication authenticate = authManager.authenticate(token);

            if (authenticate.isAuthenticated()) {
                response.setMessage("Login Sucessful");
                response.setStatus(200);
                response.setData("User has logged");
                return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.setMessage("Failed");
        response.setStatus(401);
        response.setData("Un-Authorized Access");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }
}

