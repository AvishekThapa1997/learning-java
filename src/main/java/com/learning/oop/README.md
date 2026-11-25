# Java OOP --- Revision Notes

> These notes summarize the Java OOP concepts covered so far. They focus
> on the fundamentals needed before moving into Spring Boot. Advanced
> details are intentionally deferred until they become useful in
> implementation.

## 1. Packages

A **package** is a namespace used to group related Java types, such as
classes, interfaces, and enums.

```java
package com.learning.oop;
```

Key points:

- The package declaration is the first non-comment statement in a Java
  source file.
- Directory structure conventionally mirrors the package name, but the
  package declaration determines the type's package identity.
- `import` lets you refer to a type by its simple name instead of its
  fully qualified name.
- An import does not dynamically load a class at runtime.
- Subpackages are separate packages. For example, `com.example` and
  `com.example.service` are different packages.

## 2. Access Modifiers

Access modifiers control where a class or member can be accessed.

---

Modifier Accessibility

---

`private` Only inside the declaring class

No modifier (package-private) Anywhere in the same package

`protected` Same package, plus qualifying
access from subclasses in other
packages

`public` Anywhere the declaring type itself
is accessible

---

Important rules:

- A top-level class can be `public` or package-private. It cannot be
  `private` or `protected`.
- Class accessibility and member accessibility are separate. A
  `public` member inside a package-private class is still inaccessible
  outside that package.
- `protected` does not mean accessible to every class in a subpackage.
- Across packages, a subclass can access an inherited `protected`
  member through `this` or a reference whose type is the subclass (or
  its subtype). It cannot access it through an arbitrary
  superclass-typed reference.
- A `private` member is not directly accessible by other classes, even
  classes in the same package.

## 3. Constructors and Constructor Chaining

A constructor initializes an object.

### `this()` and `super()`

- `this(...)` invokes another constructor in the same class.
- `super(...)` invokes a constructor of the immediate superclass.
- A constructor can use only one of these constructor-invocation
  forms, and it must be the first statement in the constructor.
- If neither is written, Java inserts an implicit `super()`.
- That implicit call requires an accessible no-argument constructor in
  the superclass. If none exists, compilation fails.
- In multilevel inheritance, constructor execution proceeds from the
  topmost superclass down to the concrete subclass.

Example:

```java
class Parent {
    Parent(String name) {
        System.out.println("Parent: " + name);
    }
}

class Child extends Parent {
    Child() {
        super("base");
        System.out.println("Child");
    }
}
```

## 4. Inheritance

Inheritance allows a class to derive from another class using `extends`.

```java
class Animal {
    void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking");
    }
}
```

Key points:

- Java supports single class inheritance: a class can extend only one
  class.
- A class can implement multiple interfaces.
- A subclass inherits accessible members, but private members are not
  directly accessible in the subclass.
- A `final` class cannot be extended.
- A `final` method cannot be overridden.
- A private method is not overridden; a same-signature method in a
  subclass is a separate method.

## 5. Static Method Hiding vs Instance Method Overriding

### Static method hiding

Static methods belong to a class and are resolved using the reference's
declared type at compile time. When a subclass declares a static method
with the same signature, it hides the superclass method; it does not
override it.

### Instance method overriding

Overridden instance methods are selected at runtime based on the actual
object's class.

```java
Parent value = new Child();

value.staticMethod();   // Selection is based on the reference type
value.instanceMethod(); // Overridden implementation is selected at runtime
```

A static method cannot override an instance method, and an instance
method cannot override a static method with the same signature.

## 6. Upcasting and Downcasting

### Upcasting

Assigning a subclass object to a superclass reference. It is implicit.

```java
Parent value = new Child();
```

- The reference type controls which members are accessible at compile
  time.
- The actual object type determines which overridden instance method
  runs.

### Downcasting

Converting a superclass reference back to a subclass reference. It
requires an explicit cast.

```java
Parent value = new Child();
Child child = (Child) value;
```

A cast does not create a new object. It changes the reference's static
type. An invalid cast can compile but fail at runtime with
`ClassCastException`.

Check the runtime type first:

```java
if (value instanceof Child child) {
    child.childMethod();
}
```

Pattern matching for `instanceof` became a permanent Java feature in
Java 16.

## 7. Method Overloading

Overloading means declaring multiple methods with the same name but
different parameter lists.

A Java method signature consists of:

- Method name
- Parameter types, including their order and number

The return type is **not** part of the method signature. Therefore,
changing only the return type does not create a valid overload.

Valid overloads can differ by parameter count, parameter types, or
parameter order.

Overload selection happens at compile time.

## 8. Method Overriding

Overriding occurs when a subclass provides an implementation of an
inherited instance method with a compatible signature.

Important rules:

- The overriding method must have the same method signature.
- The return type must be the same or covariant (a subtype of the
  original reference return type).
- Visibility cannot be reduced. It can be increased.
- Private methods are not overridden.
- Static methods are hidden, not overridden.
- Final methods cannot be overridden.
- Use `@Override` to let the compiler verify that the method really
  overrides a superclass method or implements an interface method.

## 9. Abstraction and Encapsulation

### Abstraction

Abstraction exposes essential behaviour or a contract while hiding
implementation details.

Java supports abstraction through interfaces and abstract classes.

### Encapsulation

Encapsulation groups data and behaviour in a class and controls how the
object's internal state can be accessed or changed.

For example, a bank account can keep its balance private and expose
operations such as `deposit()` and `withdraw()` that enforce rules.
Encapsulation does not mean adding a getter and setter for every field;
unrestricted setters can defeat the purpose.

## 10. Interfaces and Abstract Classes

Both can be used to define abstractions, but they serve different
purposes.

---

Interface Abstract class

---

Defines a contract and can provide Can provide shared state and
default behaviour behaviour

A class can implement multiple A class can extend only one class
interfaces

No constructors Can have constructors

Cannot have ordinary instance Can have instance fields
fields

Fields are implicitly Fields can use normal access and
`public static final` mutability rules

---

Use an abstract class when related subclasses need shared state,
constructor logic, or common implementation. Use interfaces when
defining capabilities or contracts that can be implemented across
different class hierarchies.

### Interface method types

- **Abstract method:** Declares a contract without a method body. It
  is implicitly `public abstract` unless declared otherwise through
  another permitted method form.
- **Default method:** Has an implementation and is implicitly
  `public`. Implementing classes inherit it and may override it.
- **Static method:** Belongs to the interface and is called using the
  interface name.
- **Private method (Java 9+):** An internal helper for other interface
  methods; it is not inherited or overridden by implementing classes.

Default methods were introduced in Java 8. They allow interfaces to gain
common behaviour without requiring every existing implementing class to
add an implementation.

If two unrelated interfaces provide conflicting default methods with the
same signature, the implementing class must resolve the conflict by
overriding the method. It can explicitly call an interface's
implementation using `InterfaceName.super.method()`.

## 11. Composition, Inheritance, and Aggregation

### Inheritance --- "is-a"

Use inheritance when a subtype genuinely is a kind of its parent type.

Example: `Dog` is an `Animal`.

### Composition --- "has-a"

Composition means an object contains or delegates work to another
object.

Example: `OrderService` has a `PaymentService`.

Prefer composition when the goal is to reuse or coordinate behaviour
without a genuine subtype relationship. Constructor injection is one
common way to supply a dependency.

### Aggregation

Aggregation is a weaker has-a association in which the related object
can exist independently of the containing object. For example, an
employee can exist independently of a department.

The distinction is a modelling concept; Java's garbage collector does
not automatically enforce ownership or destroy an object merely because
it is part of a composition.

## 12. `Object`, `equals()`, `hashCode()`, and `toString()`

Every Java class ultimately derives from `Object` unless it extends
another class.

### `equals()`

- `==` compares object references: whether they refer to the same
  object.
- `equals()` defines logical equality according to the class
  implementation.
- The default `Object.equals()` behaves like reference equality.
- A class can override `equals()` to define its own equality rule,
  such as comparing users by ID.

### The `equals()` contract

An implementation should satisfy:

1.  **Reflexive:** `x.equals(x)` is `true`.
2.  **Symmetric:** If `x.equals(y)` is `true`, then `y.equals(x)` is
    `true`.
3.  **Transitive:** If `x.equals(y)` and `y.equals(z)` are `true`, then
    `x.equals(z)` is `true`.
4.  **Consistent:** Repeated calls return the same result while the data
    used for equality remains unchanged.
5.  **Non-null:** For a non-null `x`, `x.equals(null)` is `false`.

### `hashCode()`

- If two objects are equal according to `equals()`, they must have the
  same hash code.
- Unequal objects may have the same hash code; this is a collision.
- When overriding `equals()`, also override `hashCode()` using the
  same equality-defining fields.

Basic example when equality is based on an integer ID:

```java
@Override
public boolean equals(Object obj) {
    User other = (User) obj;
    return this.id == other.id;
}

@Override
public int hashCode() {
    return this.id;
}
```

This is a learning example, not a complete production implementation:
null and type checks are intentionally deferred.

### `toString()`

`toString()` provides a string representation of an object. Override it
when a useful representation helps debugging or logging.
`System.out.println(object)` uses the object's `toString()` method.

## 13. Immutability

An immutable object is an object whose state cannot be changed after
construction.

`String` is an example of an immutable Java class.

Important distinction:

- `final` on a primitive field prevents reassignment of that field.
- `final` on an object reference prevents the reference from being
  reassigned; it does **not** make the referenced object immutable.
- A `final List<String>` can still have elements added or removed
  unless access is restricted.

A common starting design for an immutable class:

- Make fields `private`.
- Make fields `final` where appropriate.
- Initialize fields in the constructor.
- Do not expose setters.
- Prevent callers from mutating internal mutable objects.

### Defensive copying

For a mutable input such as a list, copy it in the constructor:

```java
this.roles = new ArrayList<>(roles);
```

This prevents later changes to the caller's original list from changing
the object's internal list.

Protect the internal list when returning it:

```java
public List<String> getRoles() {
    return Collections.unmodifiableList(roles);
}
```

The two protections solve different problems:

- Constructor copy protects against changes through the original input
  reference.
- Unmodifiable getter prevents callers from modifying the list through
  the returned reference.

`new ArrayList<>(roles)` is a shallow copy. If the elements themselves
are mutable objects, their internal state may still be shared.
