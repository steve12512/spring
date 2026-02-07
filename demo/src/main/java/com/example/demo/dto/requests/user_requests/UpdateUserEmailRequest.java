package com.example.demo.dto.requests.user_requests;

import jakarta.validation.constraints.Email;

public class UpdateUserEmailRequest {
  @Email String email;

  public String getEmail() {
    return email;
  }

  public UpdateUserEmailRequest(String email) {
    this.email = email;
  }
}
