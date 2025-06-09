package com.lavalliere.daniel.projects.patterns.behavioral.memento;

public class TextDocumentState {

    private String state;

    public TextDocumentState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}