package com.learning.streams;

import java.util.List;
import java.util.Optional;

public class FindFirstExercise {
    public static void main(String[] args) {
        List<User> users = User.dummyUsers();
        Optional<User> firstUser = users.stream().filter(user -> user.getAge() > 30).findFirst();
        String name = firstUser.map(user -> user.getName()).orElse(null);
        System.out.println(name);
    }
}
