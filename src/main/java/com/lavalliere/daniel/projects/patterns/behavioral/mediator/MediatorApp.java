package com.lavalliere.daniel.projects.patterns.behavioral.mediator;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class MediatorApp implements Demoable {

    private MediatorApp doMediate() {
        var board = new Board();
        var ticket = new Ticket();
        var assignee = new Assignee();

        var mediator = new Mediator(ticket, assignee, board);
        mediator.pickUpTicket();
        System.out.println();
        mediator.resolveTicket();

        System.out.println();
        return this;
    }

    @Override
    public void demo() {
        new MediatorApp()
            .doMediate()
            ;
    }
}
