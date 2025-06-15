package com.authservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Service class responsible for generating JWT tokens for authenticated users.
 */
@Service
public class JWTService {

    /** Secret key used for signing the JWT (keep this secure and externalized in production) */
    private static final String SECRET_KEY = "my-super-secret-key";

    /** Token validity period (1 day in milliseconds) */
    private static final long EXPIRATION_TIME = 86400000;

    /**
     * Generates a signed JWT token containing username and role as claims.
     *
     * @param username the subject of the token
     * @param role     the user's role to be embedded in the token
     * @return a signed JWT token string
     */
    public String generateToken(String username, String role) {
        return JWT.create()
                .withSubject(username) // Sets the subject of the token
                .withClaim("role", role) // Custom claim for user's role
                .withIssuedAt(new Date()) // Token issue time
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Token expiry time
                .sign(Algorithm.HMAC256(SECRET_KEY)); // Signing with HMAC SHA-256 and secret key
    }
}
