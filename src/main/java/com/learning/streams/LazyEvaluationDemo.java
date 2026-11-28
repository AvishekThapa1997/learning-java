package com.learning.streams;

import java.util.List;

public class LazyEvaluationDemo {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        var stream = numbers.stream()
                .filter(number -> {
                    System.out.println("Filtering: " + number);
                    return number > 2;
                });

        System.out.println("Pipeline created");

        stream.forEach(number -> System.out.println("Result: " + number));
    }
}
