package com.example.demo.exception;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserErrorResponse {
    String message;
    String error;
    LocalDateTime datetime;
    int status;

    public UserErrorResponse(String message, String error, int status) {
        this.message = message;
        this.error = error;
        this.datetime = LocalDateTime.now();
        this.status = status;
    }


    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public int getStatus() {
        return status;
    }
}