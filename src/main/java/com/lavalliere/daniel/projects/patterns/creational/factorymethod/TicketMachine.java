package com.lavalliere.daniel.projects.patterns.creational.factorymethod;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class TicketMachine {

    private static final Map<String, Supplier<Ticket>> ticketMap = new HashMap<>();

    static {
        ticketMap.put("Bus", BusTicket::new);
        ticketMap.put("Train", TrainTicket::new);
    }

    // The factory method
    Ticket createTicket(String ticketType) {
        if (ticketMap.get(ticketType) != null) {
            return ticketMap.get(ticketType).get();
        } else {
            throw new IllegalArgumentException("Invalid ticket type");
        }
    }
}
