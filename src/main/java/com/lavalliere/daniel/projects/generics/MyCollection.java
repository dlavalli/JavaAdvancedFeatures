package com.lavalliere.daniel.projects.generics;

import java.util.Arrays;
import java.util.Collection;

public interface MyCollection <T> {
    public boolean containsAll(Collection<T> c);
    public T getLast();

    public static <T> T doAdd(T[] a, Collection<T> c) {
        c.addAll(Arrays.asList(a));
        return a[0];
    }
}
