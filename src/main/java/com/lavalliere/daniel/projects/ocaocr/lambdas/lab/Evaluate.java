package com.lavalliere.daniel.projects.ocaocr.lambdas.lab;

@FunctionalInterface
public interface Evaluate<T> {
    Boolean isNegative(T t);
}
