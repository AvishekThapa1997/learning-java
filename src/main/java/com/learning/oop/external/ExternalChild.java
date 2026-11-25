package com.learning.oop.external;

import com.learning.oop.Parent;

public class ExternalChild extends Parent {
    public void printName() {
        // Parent parent = new Parent();
        // System.out.println(parent.name); // Protect member is allowed only inside its
        // subclass outside the package
        System.out.println(this.name);
    }
}
