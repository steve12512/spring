package com.example.demo.repository;

import com.example.demo.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserById(int id);
    List<User> findAll();
    boolean deleteUser(int id);
    boolean saveUser(User user);
    void updateUserEmail(int id, String email);
}