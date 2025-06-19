package com.propertyservice.repository;
// Declares the package where all repository interfaces are organized for the Property Service module.

import com.propertyservice.entity.PropertyPhotos;
import org.springframework.data.jpa.repository.JpaRepository;
// Imports the JPA repository interface provided by Spring Data JPA for generic CRUD operations.

public interface PropertyPhotosRepository extends JpaRepository<PropertyPhotos, Long> {
    /**
     * Repository interface for performing CRUD operations on PropertyPhotos entity.
     * Inherits basic CRUD methods like save(), findById(), findAll(), deleteById(), etc. from JpaRepository.
     *
     * @see JpaRepository
     * @param <PropertyPhotos> The entity type this repository manages.
     * @param <Long> The data type of the primary key (ID) of the PropertyPhotos entity.
     */
}
