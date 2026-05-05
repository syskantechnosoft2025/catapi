package com.example.catapi;

import org.springframework.http.HttpStatus;

public class CatApiException extends RuntimeException {

    private final HttpStatus status;

    public CatApiException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public CatApiException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
