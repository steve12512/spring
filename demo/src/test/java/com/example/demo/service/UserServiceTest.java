package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.demo.domain.User;
import com.example.demo.dto.responses.user_responses.UserResponse;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.user.UserNotFoundException;
import com.example.demo.repository.user.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock private UserRepository userRepository;

  @InjectMocks private UserService userService;

  @Test
  public void testCreateUser() {
    User user = new User("steve", "stevekalelis@hotmail.com", 26, true);

    when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
    when(userRepository.save(any(User.class))).thenReturn(user);

    UserResponse mockResponse =
        userService.createUser(user.getAge(), user.getUsername(), user.getEmail());

    assertNotNull(mockResponse);
    assertEquals(user.getUsername(), mockResponse.getUsername());

    verify(userRepository).save(any(User.class));
  }

  @Test
  public void testCreateUserThrowsUserAlreadyExistsException() {
    User user = new User("steve", "stevekalelis@hotmail.com", 26, true);

    when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

    assertThrows(
        UserAlreadyExistsException.class,
        () -> userService.createUser(user.getAge(), user.getUsername(), user.getEmail()));
  }

  @Test
  public void testFindById() {

    User user = new User("steve", "stevekalelis@hotmail.com", 26, true);

    when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

    UserResponse mockUserResponse = userService.findUserById(user.getId());

    assertNotNull(mockUserResponse);
    assertEquals(user.getEmail(), mockUserResponse.getEmail());
    assertEquals(user.getAge(), mockUserResponse.getAge());
    assertEquals(user.getIsActive(), mockUserResponse.getIsActive());
  }

  @Test
  public void testFindByIdThrowsUserNotFoundException() {
    User user = new User("steve", "stevekalelis@hotmail.com", 26, true);

    when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

    assertThrows(UserNotFoundException.class, () -> userService.findById(user.getId()));
  }

  @Test
  public void
















}
