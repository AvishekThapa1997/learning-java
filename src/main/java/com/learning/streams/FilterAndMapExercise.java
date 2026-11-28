package com.learning.streams;

import java.util.List;

public class FilterAndMapExercise {
    public static void main(String[] args) {
        List<User> users = User.dummyUsers();
        List<String> adultUsers = users.stream().filter(user -> {
            System.out.println("Applying Filter");
            return user.getAge() >= 18 && user.isActive();
        }).map(user -> {
            System.out.println("Applying Map");
            return user.getName();
        }).toList();

        List<String> capitalizeNames = users.stream().map(user -> user.getName().toUpperCase()).toList();

        System.out.println(capitalizeNames);
    }
}
