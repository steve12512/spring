package com.example.demo.exception.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WrongOrderUserIDException extends RuntimeException {
  Long userId;
}
