package com.example.demo.controller;

import com.example.demo.dto.requests.user_requests.*;
import com.example.demo.dto.requests.user_requests.UserSearchRequest;
import com.example.demo.dto.requests.user_requests.UserSummaryRequest;
import com.example.demo.dto.responses.user_responses.UserResponse;
import com.example.demo.dto.responses.user_responses.UserSummaryResponse;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{id}")
  public UserResponse getUser(@PathVariable Long id) {
    UserResponse userResponse = userService.getUserById(id);
    return userResponse;
  }

  @GetMapping
  public Page<UserResponse> getAllUsers(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "id") String sortBy) {

    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return userService.getUsers(pageable);
  }

  @GetMapping("/filter/search")
  public Page<UserResponse> getUsersOlderThan(
      @Valid @ModelAttribute UserSearchRequest request, Pageable pageable) {
    return userService.getUsersAgeGreaterThanEqual(request, pageable);
  }

  @GetMapping("filter/advanced_search")
  public Page<UserSummaryResponse> getRankedUsers(UserSummaryRequest request) {
    return userService.getRankedUsers(request);
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
    return userService.createUser(request.getAge(), request.getUsername(), request.getEmail());
  }

  @PutMapping("/{id}/email")
  public UserResponse updateUserEmail(
      @PathVariable Long id, @Valid @RequestBody UpdateUserEmailRequest request) {
    return userService.updateUserEmail(id, request.getEmail());
  }

  @PutMapping("/{id}/isActive")
  public UserResponse setUsertoInactive(@PathVariable Long id) {
    return userService.setUserStatusToInactive(id);
  }

  @DeleteMapping("/{id}")
  public UserResponse deleteUser(@PathVariable Long id) {
    return userService.deleteById(id);
  }
}
