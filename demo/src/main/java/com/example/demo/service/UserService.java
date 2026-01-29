package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserResponse;
import com.example.demo.exception.*;
import com.example.demo.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public User createUser(int id, int age, String username, String email){
        if (id <= 0) {
            throw new InvalidIdException(id);
        }
        if (repository.findUserById(id).isPresent()){
            throw new UserAlreadyExistsException(id);
        }
        User user = new User(username,id,email,age);
        repository.saveUser(user);
        return user;
    }

    public List<User> getUsers(){
        List<User> users = repository.findAll();
        return users;
    }


    public User updateUserEmail(int id, String new_email){
        Optional<User> userOpt = repository.findUserById(id);
        if (userOpt.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        User user = userOpt.get();
        if (user.getEmail().equals(new_email)){
            throw new SameEmailException(id,new_email);
        }
        repository.updateUserEmail(id, new_email);
        return user;
    }

    public User getUserById(int id){
        Optional<User>  userOpt = repository.findUserById(id);
        if (userOpt.isEmpty()){
            throw new UserNotFoundException(id);
        }
        return  userOpt.get();
    }


}