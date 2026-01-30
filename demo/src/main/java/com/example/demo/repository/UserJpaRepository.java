package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public interface UserJpaRepository extends JpaRepository<User,Integer>, UserRepository{

}