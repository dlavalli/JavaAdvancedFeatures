package com.lavalliere.daniel.projects.patterns.creational.abstractfactories;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class UserInterfaceCreator implements Demoable {

    private UserInterface createUserInterface(String color) {
        var userInterfaceFactory = FactoryMaker.createFactory(color);
        var button = userInterfaceFactory.createButton();
        var scrollBar = userInterfaceFactory.createScrollbar();
        return new UserInterface(button, scrollBar);
    }

    private UserInterfaceCreator createIt() {
        UserInterface roadUserInterface = createUserInterface("RED");
        UserInterface mountainUserInterface = createUserInterface("BLUE");
        System.out.println(roadUserInterface);
        System.out.println(mountainUserInterface);
        System.out.println();
        return this;
    }

    public void demo() {
        createIt();
    }
}
