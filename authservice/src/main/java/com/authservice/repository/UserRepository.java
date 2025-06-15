package com.authservice.repository;

import com.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for {@link User} entity.
 * <p>
 * Provides methods for CRUD operations and custom queries.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by username.
     *
     * @param username the username to search
     * @return the user entity if found, else null
     */
    User findByUsername(String username);

    /**
     * Retrieves a user by email.
     *
     * @param email the email to search
     * @return the user entity if found, else null
     */
    User findByEmail(String email);

    /**
     * Checks if a user exists by username.
     *
     * @param username the username to check
     * @return true if a user exists, false otherwise
     */
    boolean existsByUsername(String username);

    /**
     * Checks if a user exists by email.
     *
     * @param email the email to check
     * @return true if a user exists, false otherwise
     */
    boolean existsByEmail(String email);
}
