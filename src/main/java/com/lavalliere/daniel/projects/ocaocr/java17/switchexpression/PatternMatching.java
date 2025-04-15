package com.lavalliere.daniel.projects.ocaocr.java17.switchexpression;

public class PatternMatching {

    public static void whatType(Object o) {
        // Java is now pattern matching what this Object o  is referring to
        switch(o) {
            case String s  -> System.out.println("String");
            case Integer i -> System.out.println("Integer");
            case null      -> System.out.println("null");
            default        -> System.out.println("Not recognized");
        }

    }

    public static void infoOnType(Object o) {
        // NOTICE WHEN token to set a condition
        switch(o) {
            case String s when s.startsWith("A")  -> System.out.println("String");
            case Integer i when i > 10 && i < 20  -> System.out.println("Integer");
            case null      -> System.out.println("null");
            default        -> System.out.println("Not recognized");
        }

    }

    public static void infoOnString(String o) {
        switch(o) {
            case "HELLO"  -> System.out.println("HELLO");
            case "THERE"  -> System.out.println("THERE");
            case null     -> System.out.println("null");
            default       -> System.out.println("Not recognized");
        }
    }

    public static void infoOnEnum(Direction o) {
        switch(o) {
            case NORTH, SOUTH  -> System.out.println("NORTH/SOUTH");
            case EAST, WEST    -> System.out.println("EAST/WEST");
            case null     -> System.out.println("null");
            default       -> System.out.println("Not recognized");
        }
    }

    public static void infoOnVehicule(Vehicule o) {
        switch(o) {
            case Car c  -> System.out.println("Car");
            case Boat b  -> System.out.println("Boat");
            case Train t  -> System.out.println("Train");
            case null     -> System.out.println("null");
            default       -> System.out.println("Not recognized");
        }
    }
}
