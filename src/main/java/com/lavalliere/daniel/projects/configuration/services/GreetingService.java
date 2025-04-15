package com.lavalliere.daniel.projects.configuration.services;

public class GreetingService {

    private final String greeting;

    public GreetingService(String greeting){
        super();
        this.greeting = greeting;
    }

    public String getGreeting(String name){
        return greeting + " " + name;
    }
}