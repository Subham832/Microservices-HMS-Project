package com.propertyservice.repository; // Declares the package for repository interfaces in the Property Service module.

import java.time.LocalDate; // Represents date-only values.
import java.util.List; // Used to return multiple RoomAvailability records.

import org.springframework.data.jpa.repository.JpaRepository; // Provides standard CRUD operations for the entity.
import org.springframework.data.jpa.repository.Query; // Allows definition of custom JPQL queries.
import org.springframework.data.repository.query.Param; // Used to bind method parameters to named query parameters.

import com.propertyservice.entity.RoomAvailability; // Imports the RoomAvailability entity.

/**
 * RoomAvailabilityRepository provides database access methods for RoomAvailability entity.
 * Includes both standard CRUD operations and custom queries for room availability by date.
 */
public interface RoomAvailabilityRepository extends JpaRepository<RoomAvailability, Long> {

    List<RoomAvailability> findByRoomId(long id); // Retrieves all availability records for a specific room.

    @Query("SELECT ra FROM RoomAvailability ra WHERE ra.id = :id AND ra.availableDate = :date") // Custom query to get a specific room's availability for a given date.
    RoomAvailability getRooms(@Param("id") long id, @Param("date") LocalDate date); // Returns single availability entry for a room on a specific date.
}
