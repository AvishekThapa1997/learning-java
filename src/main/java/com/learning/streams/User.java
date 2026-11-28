package com.learning.streams;

import java.util.List;

public class User {
    private int id;
    private String name;
    private int age;
    private boolean active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User(int id, String name, int age, boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.active = active;
    }

    public static List<User> dummyUsers() {
        return List.of(
                new User(1, "Avishek", 29, true),
                new User(2, "Rahul", 17, true),
                new User(3, "John", 25, false),
                new User(4, "Amit", 32, true),
                new User(5, "David", 16, false),
                new User(6, "Priya", 24, true),
                new User(7, "Sneha", 19, false),
                new User(8, "Rohit", 28, true),
                new User(9, "Neha", 35, true),
                new User(10, "Karan", 15, true));
    }
}
