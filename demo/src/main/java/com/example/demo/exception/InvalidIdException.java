package com.example.demo.exception;

public class InvalidIdException extends RuntimeException{
    public InvalidIdException(int id ) {
        super("The id provided is invalid. id: " + id );
    }
}