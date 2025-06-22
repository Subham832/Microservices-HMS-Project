package com.propertyservice.dto; // Defines the package location for Data Transfer Objects (DTOs).

/**
 * Generic APIResponse class to standardize API responses across the application.
 * @param <T> the type of the response data payload
 */
public class APIResponse<T> {

    private String message; // Descriptive message about the response (e.g., "Success", "Error occurred").
    private int status; // HTTP status code associated with the response (e.g., 200, 400, 500).
    private T data; // The actual data payload returned in the response.

    public String getMessage() { // Returns the response message.
        return message;
    }

    public void setMessage(String message) { // Sets the response message.
        this.message = message;
    }

    public int getStatus() { // Returns the HTTP status code.
        return status;
    }

    public void setStatus(int status) { // Sets the HTTP status code.
        this.status = status;
    }

    public T getData() { // Returns the response data.
        return data;
    }

    public void setData(T data) { // Sets the response data.
        this.data = data;
    }
}
