package com.authservice.dto;

/**
 * Standard generic response wrapper for API responses.
 *
 * @param <T> the type of data returned in the response
 */
public class APIResponse<T> {

    /**
     * Message describing the response result
     */
    private String message;

    /**
     * HTTP status code associated with the response
     */
    private int status;

    /**
     * Response payload of generic type T
     */
    private T data;

    /**
     * Retrieves the response message.
     *
     * @return message text
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the response message.
     *
     * @param message the message text
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieves the HTTP status code.
     *
     * @return status code
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code.
     *
     * @param status the status code
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Retrieves the data payload.
     *
     * @return response data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data payload.
     *
     * @param data the response data
     */
    public void setData(T data) {
        this.data = data;
    }
}
