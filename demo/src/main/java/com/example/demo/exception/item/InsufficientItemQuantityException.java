package com.example.demo.exception.item;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InsufficientItemQuantityException extends RuntimeException {
  String message;
}
