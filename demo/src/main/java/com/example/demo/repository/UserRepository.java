package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    Page<User> findByAgeGreaterThanEqual(int minAge, Pageable pageable);
    Page <User> findByAgeGreaterThanEqualAndUsernameContaining(int minAge, String name, Pageable pageable);
    void deleteById(Long id);
    User save(User user);

}