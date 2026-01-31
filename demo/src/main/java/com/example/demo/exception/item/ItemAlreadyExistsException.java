package com.example.demo.exception.item;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ItemAlreadyExistsException extends RuntimeException{
    String message;
}