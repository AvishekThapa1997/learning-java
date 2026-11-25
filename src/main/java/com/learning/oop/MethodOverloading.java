package com.learning.oop;

public class MethodOverloading {

    public static void main(String[] args) {
        MethodOverloading ml = new MethodOverloading();
        ml.add(0, 0);
    }

    void add(int a, int b) {

    }

    int add(double a, double b) {
        return 0;
    }

    int add(float a, float b) {
        return 0;
    }
}
