package com.lavalliere.daniel.projects.functional;


public class NumberUtils {

    public final static String EVEN = "event";
    public final static String ODD = "event";

    static void evenOrOdd(int number) {
        System.out.println(STR."\{number} is \{number % 2 == 0 ? EVEN : ODD }");
    }
}
