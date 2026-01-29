package com.example.demo.repository;

import com.example.demo.domain.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepository implements PostgresUserRepository{
    Map<int, User> users = new ConcurrentHashMap<>();

    public InMemoryRepository(Map<int, User> users) {
        this.users = users;
    }

    @Override
    public Optional<User> findUserById(int id){
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void  saveUser(User user){
        users.put(user.getId(), user);
    }

    @Override
    public void updateUserEmail(int id, String email){
        Optional<User> userOpt = findUserById(id);
        User user = userOpt.get();
        user.setEmail(email);
        users.put(id, user);
    }


}