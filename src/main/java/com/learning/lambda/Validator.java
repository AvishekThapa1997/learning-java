package com.learning.lambda;

@FunctionalInterface
public interface Validator {
    boolean validate(String str);
}
