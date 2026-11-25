package com.learning.functional;

import java.util.function.Consumer;

public class ConsumerExercise {

    public static void main(String[] args) {
        Consumer<String> printer = str -> System.out.println(str);
        printer.accept("Hello java");
    }
}
