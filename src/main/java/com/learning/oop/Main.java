package com.learning.oop;

import com.learning.oop.order.OrderService;

public class Main {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        orderService.createOrder("Coffee", "Vishal Thapa");
    }
}
