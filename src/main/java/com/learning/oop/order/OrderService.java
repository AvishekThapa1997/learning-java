package com.learning.oop.order;

import com.learning.oop.user.User;

public class OrderService {
    public void createOrder(String orderItem, String userName) {
        User user = new User(userName);
        Order order = new Order(orderItem, user);
        System.out.println(order);
    }
}
