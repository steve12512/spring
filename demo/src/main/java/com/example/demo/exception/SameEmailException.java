package com.example.demo.exception;

public class SameEmailException extends  RuntimeException{
    public SameEmailException(int id, String email) {
        super("User with id : " + id + " already has this email : " + email);
    }
}