package com.learning.oop.order;

import com.learning.oop.user.User;

public class Order {
    private String name;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String showOrderDetails() {
        String orderTemplate = "Order name : %s, Ordered By: %s";
        return orderTemplate.formatted(this.name, this.user.getName());
    }

    public String toString() {
        return this.showOrderDetails();
    }
}
