package com.learning.functional;

import java.util.Optional;

public class OptionalExercise {
    public static void main(String[] args) {
        Optional<String> name = Optional.ofNullable("Avishek");
        // String result = name.orElse("thappppa");
        // String result = name.orElseGet(() -> "Thappa");
        // String result = name.orElseThrow(() -> new IllegalArgumentException("No
        // value in optional"));
        /*
         * Optional<String> username = name.filter((s) -> s != null);
         * String result = username.orElse("Thapa");
         */

        Optional<String> username = name.map((s) -> s.concat(" Thapa"));
        String result = username.get();
        System.out.println(String.format("Result is %s", result));

        Optional<String> nullableValue = Optional.ofNullable("avishek");
        nullableValue.ifPresent(s -> System.out.println(s));
        nullableValue.ifPresentOrElse((s) -> s.toUpperCase(), () -> {
            System.out.println("No value");
        });
    }
}
