package com.example.demo.repository;

import com.example.demo.domain.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepository implements UserRepository{
    Map<Integer, User> users = new ConcurrentHashMap<>();

    public InMemoryRepository(Map<Integer, User> users) {
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
    public boolean  saveUser(User user){
        return users.put(user.getId(), user) != null;
    }

    @Override
    public void updateUserEmail(int id, String email){
        Optional<User> userOpt = findUserById(id);
        User user = userOpt.get();
        user.setEmail(email);
        users.put(id, user) ;
    }

    @Override
    public boolean deleteUser(int id){
        return users.remove(id) != null;
    }

}