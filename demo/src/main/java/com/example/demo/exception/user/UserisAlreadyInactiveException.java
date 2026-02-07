package com.example.demo.exception;

public class UserisAlreadyInactiveException extends RuntimeException {
  int id;

  public UserisAlreadyInactiveException(Long id) {
    super("User with id: " + id + " is already inactive");
  }
}
