package com.example.demo.repository;

import com.example.demo.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(int id);
    List<User> findAll();
    void deleteById(int id);
    User save(User user);
}