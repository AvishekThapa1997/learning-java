# Java Functional Interfaces

Functional interfaces represent a contract with exactly one abstract method. They can be implemented using lambda expressions or method references.

Java provides commonly used functional interfaces in `java.util.function`.

## 1. Predicate<T>

Accepts one value and returns a boolean.

```java
Predicate<Integer> isAdult = age -> age >= 18;
boolean result = isAdult.test(25);
```

- Abstract method: `test(T value)`
- Useful for conditions and filtering.
- Composition methods: `and()`, `or()`, and `negate()`.

## 2. Function<T, R>

Accepts one value of type `T` and returns a value of type `R`.

```java
Function<String, Integer> length = value -> value.length();
Integer result = length.apply("Java");
```

- Abstract method: `apply(T value)`
- `andThen()`: executes the current function first, then the supplied function.
- `compose()`: executes the supplied function first, then the current function.

### Function composition

```java
Function<Integer, Integer> multiplyByTwo = n -> n * 2;
Function<Integer, Integer> addTen = n -> n + 10;

System.out.println(multiplyByTwo.andThen(addTen).apply(5)); // 20
System.out.println(multiplyByTwo.compose(addTen).apply(5)); // 30
```

## 3. Consumer<T>

Accepts one value and performs an operation without returning a result.

```java
Consumer<String> printer = value -> System.out.println(value);
printer.accept("Java");
```

- Abstract method: `accept(T value)`
- `andThen()` chains consumers in sequence.
- Each chained consumer receives the same original input.
- If an earlier consumer throws an exception, later consumers in the chain are not executed.

Example:

```java
Consumer<String> print = value -> System.out.println(value);
Consumer<String> uppercase = value -> System.out.println(value.toUpperCase());

print.andThen(uppercase).accept("java");

// Output:
// java
// JAVA
```

## 4. Supplier<T>

Takes no arguments and supplies a value.

```java
Supplier<String> message = () -> "Java Learning";
String result = message.get();
```

- Abstract method: `get()`
- The lambda body runs when `get()` is invoked, not when the Supplier is created.
- Useful for lazy value creation, factories, and providing fallback values.
- Supplier has no built-in `compose()` or `andThen()` methods.

### Supplier with Optional

`Optional` provides methods that make use of Supplier:

- `orElse(value)`: returns the value or an eagerly evaluated fallback.
- `orElseGet(Supplier)`: obtains the fallback lazily only when empty.
- `orElseThrow(Supplier)`: creates and throws the supplied exception only when empty.

## 5. Optional<T>

`Optional<T>` is a container that may or may not hold a non-null value. It is not a functional interface, but its methods commonly accept functional interfaces.

Common methods:

| Method                                | Purpose                                                                        |
| ------------------------------------- | ------------------------------------------------------------------------------ |
| `of(value)`                           | Creates an Optional for a non-null value.                                      |
| `ofNullable(value)`                   | Creates an Optional that may be empty.                                         |
| `empty()`                             | Creates an empty Optional.                                                     |
| `isPresent()`                         | Checks whether a value exists.                                                 |
| `isEmpty()`                           | Checks whether no value exists.                                                |
| `get()`                               | Returns the value; throws `NoSuchElementException` if empty.                   |
| `map(Function)`                       | Transforms a present value and wraps the result in an Optional.                |
| `filter(Predicate)`                   | Keeps the value if the predicate matches; otherwise returns an empty Optional. |
| `ifPresent(Consumer)`                 | Runs the action only when a value exists.                                      |
| `ifPresentOrElse(Consumer, Runnable)` | Runs the appropriate action depending on whether a value exists.               |
| `orElse(value)`                       | Returns the value or an eagerly evaluated fallback.                            |
| `orElseGet(Supplier)`                 | Obtains the fallback lazily only when empty.                                   |
| `orElseThrow(Supplier)`               | Creates and throws the supplied exception only when empty.                     |

Important observations:

- Calling `get()` on an empty Optional throws `NoSuchElementException`.
- `filter()` returns an empty Optional when the predicate returns `false`; it does not throw.
- `map()` transforms the value and returns another Optional.
- `ifPresent()` does nothing when the Optional is empty.
- `orElse()` evaluates its fallback eagerly.
- `orElseGet()` evaluates its Supplier lazily.

## 6. BiFunction<T, U, R>

Accepts two inputs (`T` and `U`) and returns one result (`R`).

```java
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
Integer result = add.apply(10, 20);
```

- Abstract method: `apply(T first, U second)`.
- First generic type represents the first input.
- Second generic type represents the second input.
- Third generic type represents the return type.
- `andThen(Function)` passes the BiFunction's single result to a one-argument Function.
- It does not provide `compose()`.

### BiFunction composition

```java
BiFunction<Integer, Integer, Integer> calculateAnnualSalary =
    (monthlySalary, bonus) -> (monthlySalary * 12) + bonus;

Function<Integer, String> formatSalary =
    salary -> "Annual salary: ₹" + salary;

Function<Integer, String> annualSalary =
    calculateAnnualSalary.andThen(formatSalary);

System.out.println(annualSalary.apply(50000, 100000));

// Output:
// Annual salary: ₹700000
```

The BiFunction accepts two inputs and produces one result. That single result becomes the input to the Function.

## 7. BinaryOperator<T>

A specialized version of `BiFunction<T, T, T>`.

- Accepts two values of the same type.
- Returns a value of that same type.
- Useful when combining two values into one value of the same type.

```java
BinaryOperator<Integer> add = (a, b) -> a + b;

System.out.println(add.apply(10, 20)); // 30
```

Equivalent BiFunction:

```java
BiFunction<Integer, Integer, Integer> add =
    (a, b) -> a + b;
```

Both have the same behavior, but `BinaryOperator<T>` communicates that the two inputs and output share the same type.

## 8. UnaryOperator<T>

A specialized version of `Function<T, T>`.

- Accepts one value of type `T`.
- Returns a value of the same type.
- Useful for transformations where input and output types are identical.

```java
UnaryOperator<String> uppercase =
    value -> value.toUpperCase();

System.out.println(uppercase.apply("java")); // JAVA
```

Equivalent Function:

```java
Function<Integer, Integer> doubleValue =
    n -> n * 2;
```

Using `UnaryOperator<Integer>` instead communicates more clearly that the input and output types are identical.

## 9. Bi-prefixed interfaces

The `Bi` prefix generally indicates that an interface accepts two input arguments.

Relevant interfaces to recognize:

| Interface             | Contract                                |
| --------------------- | --------------------------------------- |
| `BiPredicate<T, U>`   | Two inputs → boolean                    |
| `BiConsumer<T, U>`    | Two inputs → no result                  |
| `BiFunction<T, U, R>` | Two inputs → result                     |
| `BinaryOperator<T>`   | Two same-type inputs → same-type result |

`BiFunction` and `BinaryOperator` have been covered.

`BiPredicate` and `BiConsumer` are intentionally deferred until a practical use case requires them.

## 10. Quick reference

| Interface             | Input(s) | Output  |
| --------------------- | -------- | ------- |
| `Predicate<T>`        | T        | boolean |
| `Function<T, R>`      | T        | R       |
| `Consumer<T>`         | T        | void    |
| `Supplier<T>`         | None     | T       |
| `BiFunction<T, U, R>` | T, U     | R       |
| `BinaryOperator<T>`   | T, T     | T       |
| `UnaryOperator<T>`    | T        | T       |
| `BiPredicate<T, U>`   | T, U     | boolean |
| `BiConsumer<T, U>`    | T, U     | void    |
