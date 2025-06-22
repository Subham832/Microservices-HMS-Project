package com.bookingservice.repository;
// Declares the package this interface belongs to.

import org.springframework.data.jpa.repository.JpaRepository;
// Imports the JpaRepository interface from Spring Data JPA.

import com.bookingservice.entity.BookingDate;
// Imports the BookingDate entity class to be used as the domain type.

public interface BookingDateRepository extends JpaRepository<BookingDate, Long> {
// Defines a repository interface for BookingDate entities.
// Extends JpaRepository to inherit CRUD operations and JPA data access functionality.
// The first generic parameter is the entity type (BookingDate).
// The second generic parameter is the type of the entity's primary key (Long).

}
