package com.lavalliere.daniel.projects.patterns.structural.decorator;

public class AdminUser extends UserDecorator {

    public AdminUser(User user) {
        super(user);
    }

    @Override
    public void printPrivileges() {
        super.printPrivileges();
        System.out.println("Can create new users");
    }

}