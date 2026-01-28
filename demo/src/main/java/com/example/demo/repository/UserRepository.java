package com.example.demo.repository;

import com.example.demo.domain.User;

import java.util.List;

public interface UserRepository {
    User findUserById(int id);
    List<User> findAll();
    void deleteUser(int id);
    void saveUser(User user);
    void modifyUserEmail(int id, String email);
}