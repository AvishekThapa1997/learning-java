package com.learning.exceptions;

import java.io.IOException;

public class ExceptionRevision {

    static void process() throws IOException {
        try {
            throw new IOException("File error");
        } finally {
            System.out.println("Cleanup");
        }
    }

    public static void main(String[] args) throws IOException {
        process();
    }
}
