package com.apigatewayproject.filter;

// JWT utility imports for token decoding and verification

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

// Spring Cloud Gateway and reactive imports
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// Java utility imports
import java.util.List;
import java.util.Map;

@Component // Marks this class as a Spring component so it gets registered and scanned automatically
public class JwtAuthenticationFilter implements GlobalFilter, Ordered {

    // Secret key used for JWT token verification
    private static final String SECRET_KEY = "my-super-secret-key";

    // Public endpoints that do not require authentication
    private static final List<String> openApiEndpoints = List.of(
            "/auth/api/v1/auth/login",
            "/auth/api/v1/auth/register"
    );

    // Protected endpoints and the roles allowed to access them
    private static final Map<String, List<String>> protectedEndpointsWithRoles = Map.of(
//            "/auth/api/v1/welcome/message", List.of("ROLE_ADMIN"),
            "/micro1/message", List.of("ROLE_ADMIN")
    );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Get the path of the incoming request
        String requestPath = exchange.getRequest().getURI().getPath();

        // If the request is for a public endpoint, allow it through without authentication
        if (isPublicEndpoint(requestPath)) {
            return chain.filter(exchange);
        }

        // Retrieve the Authorization header
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        // If Authorization header is missing or doesn't start with "Bearer ", deny access
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // Extract the token by removing the "Bearer " prefix
        String token = authHeader.substring(7);

        try {
            // Verify the token using the secret key
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(token);

            // Extract the "role" claim from the JWT
            String role = jwt.getClaim("role").asString();

            // Log the request path and user role
            System.out.println("Request path: " + requestPath);
            System.out.println("Role from token: " + role);

            // Check if the role is authorized to access the requested path
            if (!isAuthorized(requestPath, role)) {
                // If not authorized, return 403 Forbidden
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }

            // Optionally pass the user role downstream as a custom header
            exchange = exchange.mutate().request(r -> r.header("X-User-Role", role)).build();

        } catch (JWTVerificationException e) {
            // If the token is invalid or expired, return 401 Unauthorized
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        // If token is valid and role is authorized, continue the request
        return chain.filter(exchange);
    }

    // Method to check if the requested path is a public endpoint
    private boolean isPublicEndpoint(String path) {
        return openApiEndpoints.stream().anyMatch(path::equalsIgnoreCase);
    }

    // Method to check if the user role is authorized for the requested path
    private boolean isAuthorized(String path, String role) {
        for (Map.Entry<String, List<String>> entry : protectedEndpointsWithRoles.entrySet()) {
            String protectedPath = entry.getKey(); // Protected route prefix
            List<String> allowedRoles = entry.getValue(); // Roles allowed to access this path

            // If the request path starts with the protected path
            if (path.startsWith(protectedPath)) {
                // Log matched path and roles
                System.out.println("Matched protected path: " + protectedPath + " | Allowed roles: " + allowedRoles);
                // Check if the role from token is allowed
                return allowedRoles.contains(role);
            }
        }
        // If no protection rule matched, allow access (can be changed to false to block by default)
        return true;
    }

    @Override
    public int getOrder() {
        // Set the order of this filter; lower values run earlier in the chain
        return -1;
    }
}
