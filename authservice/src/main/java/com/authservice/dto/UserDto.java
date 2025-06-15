package com.authservice.dto;

/**
 * Data Transfer Object representing user details.
 * <p>
 * Used for transferring user data between layers or modules.
 */
public class UserDto {

    /**
     * Unique identifier of the user
     */
    private long id;

    /**
     * Full name of the user
     */
    private String name;

    /**
     * Username used for login
     */
    private String username;

    /**
     * Email address of the user
     */
    private String email;

    /**
     * Password for authentication
     */
    private String password;

    /**
     * Role assigned to the user (e.g., ADMIN, USER)
     */
    private String role;

    /**
     * Retrieves the user role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user role.
     *
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Retrieves the user ID.
     *
     * @return the user ID
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the user ID.
     *
     * @param id the user ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Retrieves the user's name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the email address.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
