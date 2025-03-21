package com.lavalliere.daniel.projects.patterns.creational.singleton;

// NOTE : Cannot use this implementation if need to implement a super class
public enum PrintSpoolerEnum {
    INSTANCE;

    public static PrintSpoolerEnum getInstance() {
        return INSTANCE;
    }

    void print() {
        System.out.println("Printing from PrintSpoolerEnum...");
    }
}
