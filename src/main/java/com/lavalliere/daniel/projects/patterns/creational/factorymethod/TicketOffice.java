package com.lavalliere.daniel.projects.patterns.creational.factorymethod;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class TicketOffice implements Demoable {

    private final TicketMachine ticketMachine = new TicketMachine();

    private void buyABusTicket() {
        var ticket = ticketMachine.createTicket("Bus");
        System.out.println("One bus ticket purchased. The price is $" + ticket.getPrice() + ".");
    }

    private void buyATrainTicket() {
        var ticket = ticketMachine.createTicket("Train");
        System.out.println("One train ticket purchased. The price is $" + ticket.getPrice() + ".");
    }

    private TicketOffice buyTickets() {
        buyABusTicket();
        buyATrainTicket();
        System.out.println();
        return this;
    }

    public void demo() {
        buyTickets();
    }
}
