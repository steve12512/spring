package com.example.demo.controller;

import com.example.demo.dto.requests.auth.CreateUserRequest;
import com.example.demo.dto.requests.auth.LogInUserRequest;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

  private final AuthService authService;

  @PostMapping("sign-up")
  public ResponseEntity<String> createUser(@Valid @RequestBody CreateUserRequest request) {
    String jwtToken = authService.createUser(request);
    return ResponseEntity.ok(jwtToken);
  }

  @PostMapping("log-in")
  public ResponseEntity<String> logInUser(@Valid @RequestBody LogInUserRequest request) {
    String jwt = authService.logInUser(request);
    return ResponseEntity.ok(jwt);
  }
}
