package com.example.demo.dto.responses.user_responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponse {
  Long id;
  String username;
  String email;
  int age;
  @NonNull String message;
  boolean isActive;
}
