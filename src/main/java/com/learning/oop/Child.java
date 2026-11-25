package com.learning.oop;

public class Child extends Parent {
    @Override
    public void print() {
        System.out.println("CHILD");
    }

    static void display() {
        System.out.println("CHILD STATIC DISPLAY");
    }
}
