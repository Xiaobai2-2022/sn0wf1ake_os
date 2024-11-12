/**
 * Provides the response API send to frontend
 *
 * @since 0.0.3
 */
package com.sn0wf1ake.backend;

public class APIResponse<T> {
    
    public enum Status {
        SUCCESS,
        WARN,
        FAILURE,
        UNDEFINED
    }

    private Status status;
    private T data;
    private String message;

    public APIResponse(Status status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    // Accessor and Mutators
    public Status getStatus() {
        return this.status;
    }

    public T getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static <T> APIResponse<T> success(T data) {
        return new APIResponse<>(Status.SUCCESS, data, null);
    }
    
    public static <T> APIResponse<T> failure(String message) {
        return new APIResponse<>(Status.FAILURE, null, message);
    }

}
