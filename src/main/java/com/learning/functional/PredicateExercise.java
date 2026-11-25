package com.learning.functional;

import java.util.function.Predicate;

public class PredicateExercise {
    public static void main(String[] args) {
        Predicate<Integer> isPositive = num -> num > 0;
        Predicate<Integer> isEven = num -> num % 2 == 0;
        Predicate<String> startsWithJava = str -> str.startsWith("Java");
    }
}
