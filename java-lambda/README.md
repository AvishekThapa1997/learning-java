# Java Lambda Expressions

## 1. Introduction

Lambda expressions were introduced in Java 8 to provide a concise way to implement functional interfaces.

They reduce boilerplate code and allow behaviour to be passed as an argument.

Unlike anonymous classes, lambdas don't require an explicit class implementation.

## 2. Functional Interfaces

A functional interface is an interface with exactly one abstract method.

Example:

```java
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}
```

`@FunctionalInterface` is optional but recommended. It instructs the compiler to enforce the single abstract method requirement.

### Important rules

- A functional interface must have exactly one abstract method.
- It can contain multiple default methods.
- It can contain multiple static methods.
- It can contain private methods (Java 9+).
- Default and static methods do not count towards the single abstract method requirement.

Interface methods without a body are implicitly `public abstract`.

## 3. Lambda Syntax

General syntax:

```java
(parameters) -> expression
```

The `->` operator separates lambda parameters from the lambda body.

### Anonymous class implementation

```java
Calculator addition = new Calculator() {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
};
```

### Lambda implementation

```java
Calculator addition = (a, b) -> a + b;
```

### Lambda with explicit parameter types

```java
Calculator addition = (int a, int b) -> a + b;
```

Both implementations are valid.

## 4. Expression Body vs Block Body

### Expression body

```java
Calculator addition = (a, b) -> a + b;
```

- No curly braces required.
- No explicit return keyword required when returning a value.
- The expression's result is returned implicitly.

### Block body

```java
Calculator addition = (a, b) -> {
    int result = a + b;
    return result;
};
```

- Uses curly braces.
- Supports multiple statements.
- Requires an explicit return for non-void functional methods.

For a void functional method, `return;` is optional.

## 5. Target-Type Inference

Java infers lambda parameter types from the target functional interface.

```java
interface Calculator {
    int calculate(int a, int b);
}

Calculator addition = (a, b) -> a + b;
```

The compiler infers:

- `a` is int.
- `b` is int.
- The return type is int.

This is called target-type inference.

## 6. Lambda Parameter Compatibility

Lambda parameter count and types must match the functional interface's abstract method.

```java
interface Validator {
    boolean validate(String input);
}
```

Valid:

```java
Validator validator = input -> !input.isEmpty();
```

Invalid:

```java
Validator validator = (input, another) -> true;
```

The second example has two parameters, whereas the functional method accepts one.

## 7. Return-Type Compatibility

Every execution path in a block-bodied lambda must satisfy the functional method's return type.

Invalid:

```java
Validator validator = input -> {
    if (input.isEmpty()) {
        return false;
    }
};
```

Valid:

```java
Validator validator = input -> {
    if (input.isEmpty()) {
        return false;
    }

    return true;
};
```

Equivalent expression body:

```java
Validator validator = input -> !input.isEmpty();
```

## 8. Important Java-Specific Notes

- Java lambdas target functional interfaces.
- Java uses target typing to infer parameter types.
- Java generics use reference types, so `Predicate<Integer>` uses `Integer`, not primitive `int`.
- Java lambdas are not directly invoked like JavaScript functions. Their functional interface method is invoked instead.
- Java lambdas reduce boilerplate compared with anonymous classes.

## 9. Common Applications

Lambdas are extensively used with:

- Collection operations
- Stream API
- Functional interfaces
- Asynchronous programming
- Event handling and callbacks

## 10. Revision Checklist

- [x] Understand functional interfaces.
- [x] Understand `@FunctionalInterface`.
- [x] Understand lambda syntax.
- [x] Understand expression and block bodies.
- [x] Understand target-type inference.
- [x] Understand parameter compatibility.
- [x] Understand return-type compatibility.
