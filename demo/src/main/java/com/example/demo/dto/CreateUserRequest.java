package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateUserRequest {
    @Min(1)
    int id;
    @NotBlank
    String username;
    @Email
    String email;
    @NotNull
    int age;

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }
    //$ curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"id":1, "username": "Jannis", "email": "jannis_lag@sarantaporo.gr", "age" : 30}'

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public CreateUserRequest(int id, String username, String email, int age) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.age = age;
    }
}