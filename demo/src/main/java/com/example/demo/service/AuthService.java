package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.requests.auth.CreateUserRequest;
import com.example.demo.dto.requests.auth.LogInUserRequest;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  @Transactional
  public String createUser(CreateUserRequest request) {
    if (repository.findByUsername(request.username()).isPresent())
      throw new UserAlreadyExistsException(request.username());
    User user = new User();
    user.setUsername(request.username());
    user.setEmail(request.email());
    user.setAge(request.age());
    user.setPassword(passwordEncoder.encode(request.password()));
    user.setRole("ROLE_USER1");
    repository.save(user);
    return jwtService.generateToken(user);
  }

  @Transactional
  public String logInUser(LogInUserRequest request) {
    User user =
        repository
            .findByUsername(request.username())
            .orElseThrow(() -> new UserNotFoundException(request.username()));
    if (!passwordEncoder.matches(request.password(), user.getPassword()))
      throw new RuntimeException("Invalid password or username");
    return jwtService.generateToken(user);
  }
}
