package com.lavalliere.daniel.projects.patterns.creational.singleton;

public final class PrintSpooler {

    private static PrintSpooler INSTANCE;

    private PrintSpooler() {}

    public static synchronized PrintSpooler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PrintSpooler();
        }
        return INSTANCE;
    }

    void print() {
        System.out.println("Printing from PrintSpooler ...");
    }

}