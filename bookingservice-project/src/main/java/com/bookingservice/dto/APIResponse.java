package com.bookingservice.dto; // Package for Data Transfer Objects in Booking Service.

/**
 * APIResponse is a generic wrapper for REST API responses.
 * It standardizes message, status code, and returned data across endpoints.
 *
 * @param <T> The type of data being returned in the response.
 */
public class APIResponse<T> {

    private String message; // Human-readable message (e.g., "Success", "Not Found").
    private int status; // HTTP-style status code (e.g., 200, 404).
    private T data; // Actual response data of generic type.

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
