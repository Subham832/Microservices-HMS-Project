package com.authservice.entity;

import jakarta.persistence.*;

/**
 * Entity representing a user in the system.
 * <p>
 * Mapped to the <code>user</code> table in the database.
 */
@Entity
@Table(name = "user")
public class User {

    /**
     * Primary key: Unique identifier for each user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Full name of the user
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Unique username used for login
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * Unique email address associated with the user
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * Hashed password for authentication
     */
    @Column(name = "password")
    private String password;

    /**
     * Role assigned to the user (e.g., USER, ADMIN)
     */
    @Column(name = "role")
    private String role;

    /**
     * Gets the user's role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Gets the user's ID.
     *
     * @return the user ID
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param id the user ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the user's full name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's full name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the user's username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
