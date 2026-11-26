# Java Exception Handling — Revision Notes

These notes summarize the Java exception-handling fundamentals covered in this module. Production-level exception design will be revisited during Spring Boot.

## 1. Exception hierarchy

All Java exceptions and errors derive from `Throwable`.

```text
Throwable
├── Error
└── Exception
    └── RuntimeException
```

- `Throwable`: Root type for objects that can be thrown and caught.
- `Error`: Serious JVM or system-level problems, such as `OutOfMemoryError` and `StackOverflowError`. Applications generally should not try to recover from these routinely.
- `Exception`: Conditions applications may handle.
- `RuntimeException`: Subclass of `Exception`; its subclasses are unchecked exceptions.

Examples:

| Exception                  | Category                       |
| -------------------------- | ------------------------------ |
| `IOException`              | Checked                        |
| `SQLException`             | Checked                        |
| `FileNotFoundException`    | Checked; extends `IOException` |
| `NullPointerException`     | Unchecked                      |
| `IllegalArgumentException` | Unchecked                      |
| `IllegalStateException`    | Unchecked                      |

Java only allows instances of `Throwable` subclasses to be thrown.

## 2. Checked vs unchecked exceptions

### Checked exceptions

The compiler requires a checked exception to be caught or declared with `throws`. The calling chain must eventually handle it or declare it further.

```java
public void readFile() throws IOException {
    FileReader reader = new FileReader("data.txt");
}
```

### Unchecked exceptions

`RuntimeException` and its subclasses are unchecked. The compiler does not require methods to catch or declare them.

```java
public void withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("Amount must be positive");
    }
}
```

Checked does not mean predictable, and unchecked does not mean unimportant. The distinction is whether the compiler enforces handling or declaration.

## 3. `try`, `catch`, and `finally`

- `try` contains code that may throw an exception.
- `catch` handles a matching exception type.
- `finally` is primarily for cleanup and normally executes whether an exception occurs or not.

```java
try {
    // Operation that may fail
} catch (IOException e) {
    System.out.println(e.getMessage());
} finally {
    System.out.println("Cleanup");
}
```

Important points:

- `finally` normally executes even if the `try` block returns.
- If no matching `catch` exists, `finally` executes before the exception propagates to the caller.
- `finally` is not itself an exception handler.
- Exceptional termination, such as JVM termination, can prevent `finally` from executing.
- Avoid returning from `finally`; it can override a pending return or suppress an exception.

### Multiple catch blocks

Catch more specific exception types before their parent types. A child exception catch after a parent catch is unreachable and causes a compile-time error.

```java
try {
    // Operation
} catch (ArithmeticException e) {
    // Specific case
} catch (RuntimeException e) {
    // Broader case
} catch (Exception e) {
    // General case
}
```

### Multi-catch

Unrelated exception types can share one handler:

```java
try {
    // Operation
} catch (IOException | SQLException e) {
    System.out.println(e.getMessage());
}
```

## 4. `throw` vs `throws`

- `throw` explicitly throws an exception at a particular point.
- `throws` declares that a method may propagate one or more exception types to its caller.

```java
throw new IllegalArgumentException("Invalid amount");

public void readFile() throws IOException {
    // File operation
}
```

`throws` does not handle or convert an exception. For a checked exception, the caller must handle it or declare it further. Declaring an unchecked exception with `throws` is optional.

## 5. Exception propagation

An exception travels up the call stack until a matching handler is found.

```java
void methodA() throws IOException {
    methodB();
}

void methodB() throws IOException {
    throw new IOException("Failed");
}
```

- The exception originates in `methodB()`.
- `methodB()` propagates it to `methodA()`.
- `methodA()` propagates it to its caller.
- A caller can catch it or continue declaring it with `throws`.

Propagation does not create a new exception at each method.

## 6. Custom exceptions

A custom exception is a class that extends an existing exception type.

### Unchecked custom exception

Extend `RuntimeException`:

```java
public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(String message) {
        super(message);
    }
}
```

### Checked custom exception

Extend `Exception` (but not `RuntimeException`):

```java
public class PaymentException extends Exception {
    public PaymentException(String message) {
        super(message);
    }
}
```

The superclass determines whether the custom exception is checked or unchecked. `super(message)` passes the message to the parent exception constructor, making it available through `getMessage()`.

In the banking exercise, `InvalidAmountException` and `InsufficientBalanceException` were implemented as unchecked exceptions by extending `RuntimeException`.

## 7. Try-with-resources

Try-with-resources automatically closes resources when execution leaves the try block, including when an exception occurs.

```java
try (FileReader reader = new FileReader("data.txt")) {
    // Read from the file
} catch (IOException e) {
    System.out.println(e.getMessage());
}
```

The resource must implement `AutoCloseable` (or `Closeable`, which extends it).

Examples include `FileReader`, `BufferedReader`, JDBC `Connection`, `PreparedStatement`, and `ResultSet`.

When multiple resources are declared, Java closes them in reverse order of declaration.

```java
try (ResourceA a = new ResourceA();
     ResourceB b = new ResourceB()) {
    // Work
}
// Closes b first, then a
```

### Python comparison

Try-with-resources is conceptually similar to Python's `with` statement/context manager.

| Python                                             | Java                                 |
| -------------------------------------------------- | ------------------------------------ |
| `with` statement                                   | `try-with-resources`                 |
| Context manager protocol (`__enter__`, `__exit__`) | `AutoCloseable` resource             |
| Exit handling closes/releases resource             | Java invokes `close()` automatically |

The mechanisms differ, but both provide structured resource management.

## 8. Key revision points

- `NullPointerException` is unchecked because it extends `RuntimeException`.
- `IOException`, `SQLException`, and `FileNotFoundException` are checked.
- A checked exception must be handled or declared throughout the calling chain.
- `throw` throws an exception; `throws` declares possible propagation.
- `finally` normally runs before control leaves the try/catch construct, including during exception propagation.
- Catch specific exception types before general parent types.
- A custom exception extending `Exception` is checked; one extending `RuntimeException` is unchecked.
- Try-with-resources requires `AutoCloseable` and closes resources automatically.

## 9. Deferred topics

Revisit these during Spring Boot implementation:

- Global REST exception handling with `@ControllerAdvice` and `@ExceptionHandler`.
- Exception translation in Spring and persistence layers.
- Production exception design, logging, and error responses.
- More detailed resource and transaction management.
