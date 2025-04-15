package com.lavalliere.daniel.projects.patterns.structural.bridge;

public class AdultSize implements Size {
    @Override
    public void get() {
        System.out.println("Adult");
    }
}