package com.learning.functional;

import java.util.function.BiFunction;

public class BiFunctionExercise {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> addition = (a, b) -> a + b;
        System.out.println(addition.apply(10, 20));
        BiFunction<Integer, Integer, Integer> multiplication = (a, b) -> a * b;
        BiFunction<Integer, Integer, Integer> addTen = addition.andThen(num -> num + 10);
        System.out.println(addTen.apply(10, 20));
    }
}
