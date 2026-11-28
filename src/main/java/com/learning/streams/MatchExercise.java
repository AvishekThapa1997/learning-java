package com.learning.streams;

import java.util.List;

public class MatchExercise {
    public static void main(String[] args) {
        List<User> users = User.dummyUsers();
        boolean anyAdultUser = users.stream().anyMatch(user -> user.getAge() < 18);
        boolean allAdultUser = users.stream().allMatch(user -> user.getAge() >= 18);
        boolean name = users.stream().anyMatch(user -> user.getName().toLowerCase().equals("john"));
    }
}
