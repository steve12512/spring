package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Repository
public abstract class PostgresUserRepository implements  UserRepository{
    public PostgresUserRepository() {
    }

    @Override
    public Optional<User> findById(int id){
        return null;
    }

    @Override
    public List<User> findAll(){
        return null;
    }


    @Override
    public User save(User user){
        return user;
    }

    @Override
    public void deleteById(int id){
    }

}