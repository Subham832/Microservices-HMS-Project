package com.propertyservice.repository; // Declares the package for repository interfaces.

import org.springframework.data.jpa.repository.JpaRepository; // Provides standard CRUD operations for the entity.
import com.propertyservice.entity.Rooms; // Imports the Rooms entity.

/**
 * RoomRepository provides CRUD operations for the Rooms entity.
 * Inherits methods like save(), findById(), findAll(), deleteById(), etc. from JpaRepository.
 */
public interface RoomRepository extends JpaRepository<Rooms, Long> {
    // Custom query methods (if needed) can be added here.
}
