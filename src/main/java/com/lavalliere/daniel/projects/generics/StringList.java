package com.lavalliere.daniel.projects.generics;

import java.util.Iterator;

public class StringList implements MyList<String>{
    @Override
    public void add(String element) {
        // TODO something
    }

    @Override
    public Iterator<String> iterator() {
        // TODO - Implement this
        return null;
    }

    @Override
    public String name() {
        return "String";
    }
}
