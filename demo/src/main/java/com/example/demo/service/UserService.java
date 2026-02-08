package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.requests.user_requests.UserSearchRequest;
import com.example.demo.dto.requests.user_requests.UserSummaryRequest;
import com.example.demo.dto.responses.user_responses.UserResponse;
import com.example.demo.dto.responses.user_responses.UserSummaryResponse;
import com.example.demo.exception.SameEmailException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserisAlreadyInactiveException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.repository.user.UserRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable; // CORRECT IMPORT
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository repository;

  public UserService(UserRepository repository) {
    this.repository = repository;
  }

  @Cacheable(cacheNames = "users", key = "#id")
  public UserResponse findById(Long id) {
    User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    return new UserResponse(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getAge(),
        "Success fully retrieved User with id " + id + " username " + user.getUsername(),
        user.getIsActive());
  }

  public UserResponse createUser(int age, String username, String email) {
    if (repository.findByUsername(username).isPresent()) {
      throw new UserAlreadyExistsException(username);
    }
    User user = new User(username, email, age, true);
    repository.save(user);
    return new UserResponse(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getAge(),
        "Successfully retrieved user",
        user.getIsActive());
  }

  public List<User> getUsers() {
    List<User> users = repository.findAll();
    return users;
  }

  public Page<UserResponse> getUsers(Pageable pageable) {
    return repository
        .findAll(pageable)
        .map(
            user ->
                new UserResponse(
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    user.getAge(),
                    "Successfully retrieved user",
                    user.getIsActive()));
  }

  @Transactional
  @CacheEvict(cacheNames = "users", key = "#id")
  public UserResponse updateUserEmail(Long id, String new_email) {
    Optional<User> userOpt = repository.findById(id);
    if (userOpt.isEmpty()) {
      throw new UserNotFoundException(id);
    }
    User user = userOpt.get();
    if (user.getEmail().equals(new_email)) {
      throw new SameEmailException(id, new_email);
    }
    user.setEmail(new_email);
    repository.save(user);
    return new UserResponse(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getAge(),
        "Successfully retrieved user",
        user.getIsActive());
  }

  @Cacheable(cacheNames = "users", key = "#id")
  public UserResponse getUserById(Long id) {
    Optional<User> userOpt = repository.findById(id);
    if (userOpt.isEmpty()) {
      throw new UserNotFoundException(id);
    }
    User user = userOpt.get();
    UserResponse userResponse =
        new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getAge(),
            "Successfully retrieved user with username: " + user.getUsername(),
            user.getIsActive());
    return userResponse;
  }

  public User findUserById(Long userId) {
    // This method is used internally and as thus returns a domain object, not a dto
    Optional<User> userOpt = repository.findById(userId);
    if (userOpt.isEmpty()) throw new UserNotFoundException(userId);
    return userOpt.get();
  }

  @Transactional
  @CacheEvict(cacheNames = "users", key = "#id")
  public UserResponse deleteById(Long id) {
    Optional<User> userOpt = repository.findById(id);
    if (userOpt.isEmpty()) {
      throw new UserNotFoundException(id);
    }
    repository.deleteById(id);
    return new UserResponse("User with id: " + id + " has been successfully deleted");
  }

  public Page<UserResponse> getUsersAgeGreaterThanEqual(
      @Valid UserSearchRequest request, Pageable pageable) {
    return (request.username().isEmpty())
        ? this.getUsersOlderThan(request.minAge(), pageable)
            .map(user -> this.convertToUserResponse(user))
        : this.getUsersAgeGreaterThanEqualAndUsernameContaining(
                request.minAge(), request.username(), pageable)
            .map(user -> this.convertToUserResponse(user));
  }

  public Page<User> getUsersOlderThan(int minAge, Pageable pageable) {
    return repository.findByAgeGreaterThanEqual(minAge, pageable);
  }

  public Page<User> getUsersAgeGreaterThanEqualAndUsernameContaining(
      int minAge, String name, Pageable pageable) {
    System.out.println(name);
    return repository.findByAgeGreaterThanEqualAndUsernameContaining(minAge, name, pageable);
  }

  @Transactional
  @CacheEvict(cacheNames = "users", key = "#id")
  public UserResponse setUserStatusToInactive(Long id) {
    Optional userOpt = repository.findById(id);
    if (userOpt.isEmpty()) throw new UserNotFoundException(id);
    User user = (User) userOpt.get();
    if (user.getIsActive() == false) throw new UserisAlreadyInactiveException(id);
    user.setActive(false);
    repository.save(user);
    return new UserResponse(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getAge(),
        "Successfully retrieved user",
        user.getIsActive());
  }

  public Page<UserSummaryResponse> getRankedUsers(UserSummaryRequest request) {
    Pageable pageable = (request.pageable() != null) ? request.pageable() : PageRequest.of(0, 10);
    int minAge = (request.minAge() != null) ? request.minAge() : 18;
    int maxAge = (request.maxAge() != null) ? request.maxAge() : 100;
    String usernameContains =
        (request.usernameContains() != null) ? request.usernameContains() : "";
    Boolean isActive = request.isActive();
    return repository
        .findRankedUsers(minAge, maxAge, usernameContains, isActive, pageable)
        .map(
            user ->
                new UserSummaryResponse(
                    user.getId(), user.getUsername(), user.getAge(), user.getIsActive()));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (repository.findByUsername(username).get() == null)
      throw new UsernameNotFoundException("User with username " + username + " does not exist");
    return repository.findByUsername(username).get();
  }

  public UserResponse convertToUserResponse(User user) {
    return new UserResponse(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getAge(),
        "Successfully retrieved user",
        user.getIsActive());
  }
}
