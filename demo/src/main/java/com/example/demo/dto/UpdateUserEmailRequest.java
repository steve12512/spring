package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

public class UpdateUserEmailRequest {
    @Min(10)
    int id;
    @Email
    String email;

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public UpdateUserEmailRequest(int id, String email) {
        this.id = id;
        this.email = email;
    }
}