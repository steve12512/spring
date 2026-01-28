package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostgresUserRepository implements  UserRepository{
    public PostgresUserRepository() {
    }

    @Override
    public Optional<User> findUserById(int id){
        return null;
    }

    @Override
    public List<User> findAll(){
        return null;
    }


    @Override
    public void saveUser(User user){
    }

    @Override
    public void deleteUser(int id){
    }

    @Override
    public void updateUserEmail(int id, String new_email){
    }

}