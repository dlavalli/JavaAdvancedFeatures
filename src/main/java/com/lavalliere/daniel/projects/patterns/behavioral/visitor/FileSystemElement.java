package com.lavalliere.daniel.projects.patterns.behavioral.visitor;

public interface FileSystemElement {
    String getName();
    void accept(Visitor visitor);
}