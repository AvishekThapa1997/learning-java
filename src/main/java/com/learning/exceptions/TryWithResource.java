package com.learning.exceptions;

public class TryWithResource {
    public static void main(String[] args) {
        try (MyCloseable mc = new MyCloseable()) {

        } catch (Exception e) {

        }
    }
}

class MyCloseable implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("I am closing");
    }
}
