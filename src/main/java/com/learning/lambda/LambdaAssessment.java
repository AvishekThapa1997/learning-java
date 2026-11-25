package com.learning.lambda;

// Functional Interface
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);

    default void printResult(int result) {
        System.out.println(result);
    }
}

public class LambdaAssessment {
    public static void main(String[] args) {
        Calculator addition = (int a, int b) -> a + b;
        Calculator subtraction = (a, b) -> a - b;
        System.out.println(addition.calculate(10, 20));
        System.out.println(subtraction.calculate(10, 20));
        Validator validator = (str) -> !str.isEmpty() && str.length() > 8 && str.contains("@");
        boolean isValidString1 = validator.validate("sdsddddsd@");
        boolean isValidString2 = validator.validate("sdsd@");
        boolean isValidString3 = validator.validate("sdsdssssss");
        System.out.println(isValidString1);
        System.out.println(isValidString2);
        System.out.println(isValidString3);

    }
}
