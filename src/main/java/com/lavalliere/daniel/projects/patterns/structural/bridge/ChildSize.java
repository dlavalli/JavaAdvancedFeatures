package com.lavalliere.daniel.projects.patterns.structural.bridge;

public class ChildSize implements Size {
    @Override
    public void get() {
        System.out.println("Child");
    }
}
