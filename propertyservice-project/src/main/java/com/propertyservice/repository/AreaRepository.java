package com.propertyservice.repository; // Defines the package for Spring Data JPA repository interfaces.

import org.springframework.data.jpa.repository.JpaRepository; // Provides generic CRUD operations for the entity.
import com.propertyservice.entity.Area; // Imports the Area entity class.

/**
 * AreaRepository provides CRUD operations and custom query methods
 * for the Area entity using Spring Data JPA.
 */
public interface AreaRepository extends JpaRepository<Area, Long> {

    Area findByName(String name); // Custom method to find an Area by its name.
}
