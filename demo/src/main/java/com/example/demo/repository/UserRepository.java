package com.example.demo.repository;

import com.example.demo.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserById(int id);
    List<User> findAll();
    void deleteUser(int id);
    void saveUser(User user);
    void updateUserEmail(int id, String email);
}