package com.lavalliere.daniel.projects.generics;

import java.util.Iterator;

public interface MyList<T> {
    void add(T element);
    Iterator<T> iterator();
    String name();
}
