package com.lavalliere.daniel.projects.ocaocr.lambdas.lab;

@FunctionalInterface
public interface Printable<T> {
    void print(T t);
}
