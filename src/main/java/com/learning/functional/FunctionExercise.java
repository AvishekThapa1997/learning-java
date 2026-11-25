package com.learning.functional;

import java.util.function.Function;

public class FunctionExercise {
    public static void main(String[] args) {
        Function<String, Integer> strToInt = str -> str.length();
        Function<Integer, String> intToStr = num -> Integer.toString(num);
        Function<String, String> toUpperCase = str -> str.toUpperCase();
        Function<String, String> trim = str -> str.trim();

        Function<Integer, Integer> multiplyByTwo = n -> n * 2;
        Function<Integer, Integer> addTen = n -> n + 10;

        System.out.println(multiplyByTwo.andThen(addTen).apply(5));
        System.out.println(multiplyByTwo.compose(addTen).apply(5));

    }
}
