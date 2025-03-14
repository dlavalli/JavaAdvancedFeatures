package com.lavalliere.daniel.projects.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {

    public Main doPringGreeting() {
       Greeting greeting = () ->  System.out.println("Hello world!");
       return this;
    }

    public Main doNumberEvenOrOdd() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
        numbers.forEach(NumberUtils::evenOrOdd);  // Calling a function reference
        return this;
    }

    public static void demo() {
        new Main()
             .doPringGreeting()
        ;
    }
}
