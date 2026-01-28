package com.example.demo.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(int id) {
        super("User with id: " + id + " already exists in the db");
    }
}