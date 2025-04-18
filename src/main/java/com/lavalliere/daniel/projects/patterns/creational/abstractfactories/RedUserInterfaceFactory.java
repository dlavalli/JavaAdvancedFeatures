package com.lavalliere.daniel.projects.patterns.creational.abstractfactories;

public class RedUserInterfaceFactory implements UserInterfaceFactory {

    @Override
    public Button createButton() {
        return new RedButton();
    }

    @Override
    public ScrollBar createScrollbar() {
        return new RedScrollBar();
    }
}
