package com.authservice.dto;

/**
 * Data Transfer Object for handling login requests.
 * <p>
 * Carries user credentials from client to server.
 */
public class LoginDto {

    /**
     * The username provided by the user
     */
    private String username;

    /**
     * The password provided by the user
     */
    private String password;

    /**
     * Retrieves the username.
     *
     * @return the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     *
     * @param username the user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password.
     *
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
