package com.example.demo.exception;

public class InvalidIdException extends RuntimeException{
    public InvalidIdException(Long id ) {
        super("The id provided is invalid. id: " + id );
    }
}