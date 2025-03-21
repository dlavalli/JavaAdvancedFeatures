package com.lavalliere.daniel.projects.patterns.creational.factorymethod;

public class BusTicket implements Ticket {
    @Override
    public int getPrice() {
        return 3;
    }
}
