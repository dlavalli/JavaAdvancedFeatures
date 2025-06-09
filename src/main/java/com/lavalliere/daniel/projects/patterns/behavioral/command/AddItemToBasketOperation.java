package com.lavalliere.daniel.projects.patterns.behavioral.command;

public class AddItemToBasketOperation implements ItemOperation {

    private final Item item;

    public AddItemToBasketOperation(Item item) {
        this.item = item;
    }

    @Override
    public void execute() {
        System.out.println("About to add an item to the basket");
        item.addToBasket();
    }
}