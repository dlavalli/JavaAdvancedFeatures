package com.lavalliere.daniel.projects.ocaocr.lambdas.lab;

@FunctionalInterface
public interface Functionable<T, R> {
    R applyThis(T t);
}
