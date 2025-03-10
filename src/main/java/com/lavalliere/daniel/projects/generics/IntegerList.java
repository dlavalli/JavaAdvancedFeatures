package com.lavalliere.daniel.projects.generics;

import java.util.Iterator;

public class IntegerList implements MyList<Integer>{
    @Override
    public void add(Integer element) {
        // TODO something
    }

    @Override
    public Iterator<Integer> iterator() {
        // TODO - Implement this
        return null;
    }

    @Override
    public String name() {
        return "Integer";
    }
}
