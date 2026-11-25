package com.learning.functional;

import java.util.function.Supplier;

public class SupplierExercise {
    public static void main(String[] args) {
        Supplier<Integer> numSupplier = () -> 10;
        System.out.println(numSupplier.get());

    }
}
