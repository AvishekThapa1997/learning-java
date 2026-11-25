package com.learning.functional;

import java.util.function.Consumer;

public class ConsumerExercise {

    public static void main(String[] args) {
        Consumer<String> printer = str -> System.out.println(str);
        Consumer<String> upper = str -> System.out.println(str.toUpperCase());
        Consumer<String> printLength = str -> System.out.println(str.length());
        printer.andThen(upper).andThen(printLength).accept("java");
    }
}
