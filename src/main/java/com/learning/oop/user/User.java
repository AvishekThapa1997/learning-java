package com.learning.oop.user;

import java.util.UUID;

public class User {
    static int count = 0;
    private String name;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String name) {
        this.id = ++count;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getDetails() {
        System.out.println("User Details");
    }

    @Override
    public int hashCode() {

        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User)obj;
        return this.id == user.id;
    }
}
