package com.propertyservice.repository; // Declares the package where all repository interfaces are organized for the Property Service module.

import com.propertyservice.entity.PropertyPhotos; // Imports the PropertyPhotos entity class.
import org.springframework.data.jpa.repository.JpaRepository; // Provides JPA-based CRUD operations.

/**
 * PropertyPhotosRepository provides CRUD operations for the PropertyPhotos entity.
 * Inherits methods like save(), findById(), findAll(), deleteById(), etc. from JpaRepository.
 */
public interface PropertyPhotosRepository extends JpaRepository<PropertyPhotos, Long> {
    // Additional custom query methods (if needed) can be defined here.
}
