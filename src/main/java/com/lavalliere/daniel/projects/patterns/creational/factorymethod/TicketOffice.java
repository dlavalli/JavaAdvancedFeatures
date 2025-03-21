package com.lavalliere.daniel.projects.patterns.creational.factorymethod;

public class TicketOffice {

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

    public static void demo() {
        new TicketOffice()
            .buyTickets()
        ;
    }
}
