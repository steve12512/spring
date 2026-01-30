package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("memory")

public abstract class InMemoryRepository implements UserRepository{
    Map<Long, User> users = new ConcurrentHashMap<>();

    public InMemoryRepository(Map<Long, User> users) {
        this.users = users;
    }

    @Override
    public Optional<User> findById(Long id){
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
    public void deleteById(Long id){
        users.remove(id) ;
    }


//    @Override
//    public Page<User> findAll(Pageable pageable){
//        return pageable.
//    }


}