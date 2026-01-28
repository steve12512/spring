package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserResponse;
import com.example.demo.exception.InvalidIdException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public UserResponse createUser(int id, int age, String username, String email){
        if (id <= 0) {
            throw new InvalidIdException(id);
        }
        if (repository.findUserById(id).isPresent()){
            throw new UserAlreadyExistsException(id);
        }
        User user = new User(username,id,email,age);
        repository.saveUser(user);
        return new UserResponse(id,username,email,age,"User has successfully been created");
    }

    public List<User> getUsers(){
        List<User> users = repository.findAll();
        return users;
    }


    public void changeUserEmail(int id, String new_email){
        repository.modifyUserEmail(id, new_email);
    }

    public User getUserById(int id){
        Optional<User>  userOpt = repository.findUserById(id);
        if (userOpt.isEmpty()){
            throw new UserNotFoundException(id);
        }
        return  userOpt.get();
    }


}