package com.lavalliere.daniel.projects.patterns.structural.decorator;

public class DeveloperUser extends UserDecorator {
    public DeveloperUser(User user) {
        super(user);
    }

    @Override
    public void printPrivileges() {
        super.printPrivileges();
        System.out.println("Can deploy to production");
    }
}
