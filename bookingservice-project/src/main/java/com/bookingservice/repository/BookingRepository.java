package com.bookingservice.repository;
// Declares the package where this interface resides.

import org.springframework.data.jpa.repository.JpaRepository;
// Imports JpaRepository from Spring Data JPA to provide CRUD and paging operations.

import com.bookingservice.entity.Bookings;
// Imports the Bookings entity class to be managed by this repository.

public interface BookingRepository extends JpaRepository<Bookings, Long> {
// Defines a repository interface for the Bookings entity.
// Extends JpaRepository to inherit basic CRUD operations and more.
// First generic parameter: Bookings entity type.
// Second generic parameter: Long, the type of the entity's primary key (id).

}
