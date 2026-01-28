package com.example.demo.domain;

public class User {
    String username;
    int id;
    String email;
    int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public User(String username, int id, String email, int age) {
        this.username = username;
        this.id = id;
        this.email = email;
        this.age = age;
    }
}