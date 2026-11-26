package com.learning.exceptions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CheckedExceptionExercise {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("data.txt");
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
