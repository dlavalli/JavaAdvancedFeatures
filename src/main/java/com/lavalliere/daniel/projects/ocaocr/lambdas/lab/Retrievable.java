package com.lavalliere.daniel.projects.ocaocr.lambdas.lab;

@FunctionalInterface
public interface Retrievable<T> {
    T retrieve();
}
