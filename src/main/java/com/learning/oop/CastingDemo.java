package com.learning.oop;

public class CastingDemo {
    public static void main(String[] args) {
        Child child = new Child();
        Parent p = child;
        Child child2 = (Child) p;
        p.print();
        child2.print();

    }

}
