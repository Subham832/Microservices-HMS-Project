package com.propertyservice.repository; // Declares the package for Spring Data JPA repository interfaces.

import org.springframework.data.jpa.repository.JpaRepository; // Provides basic CRUD operations for entities.
import com.propertyservice.entity.State; // Imports the State entity class.

/**
 * StateRepository provides CRUD operations and custom queries
 * for the State entity using Spring Data JPA.
 */
public interface StateRepository extends JpaRepository<State, Long> {

    State findByName(String name); // Custom method to find a state by its name.
}
