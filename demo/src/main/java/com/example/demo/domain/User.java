package com.example.demo.domain;

import jakarta.persistence.*;

@Entity
@Table (name = "users")
public class User {
    String username;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String email;
    int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    protected  User(){}

    public User(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }
}