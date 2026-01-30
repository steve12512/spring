package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("memory")
public class InMemoryRepository implements UserRepository{
    Map<Integer, User> users = new ConcurrentHashMap<>();

    public InMemoryRepository(Map<Integer, User> users) {
        this.users = users;
    }

    @Override
    public Optional<User> findById(int id){
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User save(User user){
        users.put(user.getId(), user) ;
        return user;
    }


    @Override
    public void deleteById(int id){
        users.remove(id) ;
    }

}