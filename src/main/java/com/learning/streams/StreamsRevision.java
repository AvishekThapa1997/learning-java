package com.learning.streams;

import java.util.List;
import java.util.stream.Stream;

public class StreamsRevision {
    public static void main(String[] args) {
        List<User> users = User.dummyUsers();

        // Revison - 1
        // List<String> activeAdultUserNames = users.stream().filter(user ->
        // user.isActive() && user.getAge() >= 18)
        // .map(user -> user.getName().toUpperCase()).toList();
        // System.out.println(activeAdultUserNames);

        // Revision- 2
        // String firstUserNameAboveAge25 = users.stream().filter(user ->
        // user.isActive() && user.getAge() > 25)
        // .findFirst()
        // .map(user -> user.getName()).orElse("Not Found");

        // Revision - 3
        // boolean anyYoungerActiveUser = users.stream().anyMatch(user ->
        // user.isActive() && user.getAge() < 18);

        // Revision - 4
        // boolean anyUserWithNameJohn = users.stream().anyMatch(user ->
        // user.getName().toLowerCase().equals("john"));
        // System.out.println(anyUserWithNameJohn);

    }
}
