package com.propertyservice.repository; // Declares the package for repository interfaces.

import com.propertyservice.entity.Property; // Imports the Property entity.
import org.springframework.data.jpa.repository.JpaRepository; // Provides standard CRUD operations.
import org.springframework.data.jpa.repository.Query; // Enables writing custom JPQL queries.
import org.springframework.data.repository.query.Param; // Used to bind method parameters to query parameters.

import java.time.LocalDate; // Represents a date without time zone.
import java.util.List; // Provides a list interface for collections.

/**
 * PropertyRepository provides database operations for the Property entity.
 * Includes a custom search query for filtering properties by location and availability date.
 */
public interface PropertyRepository extends JpaRepository<Property, Long> {

    @Query(""" 
                SELECT DISTINCT p 
                FROM Property p
                JOIN p.rooms r
                JOIN RoomAvailability ra ON ra.room = r
                WHERE (
                    LOWER(p.city.name) LIKE LOWER(CONCAT('%', :name, '%')) OR
                    LOWER(p.area.name) LIKE LOWER(CONCAT('%', :name, '%')) OR
                    LOWER(p.state.name) LIKE LOWER(CONCAT('%', :name, '%'))
                )
                AND ra.availableDate = :date
            """) // Custom JPQL query to search by city, area, or state and check room availability for a specific date.
    List<Property> searchProperty(@Param("name") String name, @Param("date") LocalDate date); // Method to fetch properties based on location and date.
}
