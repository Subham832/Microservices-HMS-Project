package com.propertyservice.repository; // Defines the package for repository interfaces.

import org.springframework.data.jpa.repository.JpaRepository; // Provides built-in CRUD methods.
import com.propertyservice.entity.City; // Imports the City entity.

/**
 * CityRepository provides CRUD operations and custom queries
 * for the City entity using Spring Data JPA.
 */
public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String name); // Custom method to fetch a city by its name.
}
