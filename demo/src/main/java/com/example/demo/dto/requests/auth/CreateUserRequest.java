package com.example.demo.dto.requests.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest (
        @NotBlank
        String username,
        @NotBlank
        @Email
        String email,
        @Min(18)
        Integer age,
        @Size(min=5)
        String password
){

}