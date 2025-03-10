package com.lavalliere.daniel.projects.generics;

import java.util.Collection;

public class Utility {
    public static void processAny(Collection<MyList<?>> collection) {
        for(MyList<?> element : collection) {
            // TODO - Do something
            System.out.println(element.name());
        }
    }

    // Process all subtypes that implement Number
    public static void processNumbers(Collection<MyList<? extends Number>> collection) {
        for(MyList<? extends Number> element : collection) {
            // TODO - Do something
            System.out.println(element.name());
        }
    }
}
