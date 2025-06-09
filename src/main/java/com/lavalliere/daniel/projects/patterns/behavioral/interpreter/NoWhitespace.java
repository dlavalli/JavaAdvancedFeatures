package com.lavalliere.daniel.projects.patterns.behavioral.interpreter;

public class NoWhitespace implements Expression {
    @Override
    public String interpret(String context) {
        return context.replaceAll(" ", "");
    }
}