package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.CreateUserRequest;
import com.example.demo.dto.UpdateUserEmailRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable int id) {
        User user = userService.getUserById(id);
        return new UserResponse(user.getId(), user.getUsername(),user.getEmail(), user.getAge(),"Successfully retrived user");
    }

    @GetMapping
    public List<UserResponse> getAllUsers(){
        List<User> users = userService.getUsers();
        return users.stream().map(user -> new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getAge())).toList();
    }



    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request){
        User user = userService.createUser(request.getId(), request.getAge(), request.getUsername(), request.getEmail());
        UserResponse response  = new UserResponse(user.getId(),user.getUsername(),user.getEmail(),user.getAge(),"User has successfully been created");
        return  response;
    }

    @PutMapping("/{id}/email")
    public UserResponse updateUserEmail(@PathVariable int id, @Valid @RequestBody UpdateUserEmailRequest request){
        User user = userService.updateUserEmail(id, request.getEmail());
        user.setEmail(request.getEmail());
        UserResponse userResponse = new UserResponse(user.getId(),user.getUsername(),user.getEmail(),user.getAge(),"Successfully updated the user's email to : " + user.getEmail());
        return  userResponse;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id){
        boolean hasBeenDeleted = userService.deleteUser(id);
    }





}