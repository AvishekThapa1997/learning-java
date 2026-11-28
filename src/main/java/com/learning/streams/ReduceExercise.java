package com.learning.streams;

import java.util.List;

public class ReduceExercise {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int total = nums.stream().reduce(0, (accum, curr) -> accum + curr);
        int product = nums.stream().reduce(1, (accum, curr) -> accum * curr);
        System.out.println(String.format("Total is %d", total));
        System.out.println(String.format("Product is %d", product));

        List<User> users = User.dummyUsers();
        int totalAgeOfActiveUsers = users.stream().filter(user -> user.isActive()).reduce(0,
                (accum, curr) -> accum + curr.getAge(), (a, b) -> a + b);
        System.out.println(String.format("Total age of active users: %d", totalAgeOfActiveUsers));

    }
}
