package com.lavalliere.daniel.projects.patterns.structural.decorator;

public class BasicUser implements User {

    @Override
    public void printPrivileges() {
        System.out.println("Can log in");
    }

}
