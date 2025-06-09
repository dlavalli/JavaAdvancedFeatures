package com.lavalliere.daniel.projects.patterns.behavioral.state;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class StateApp implements Demoable {

    private StateApp testState() {
        var auction = new Auction();
        auction.startAuction();
        auction.placeBid();

        auction.startAuction();

        auction.closeAuction();
        auction.placeBid();

        auction.closeAuction();
        System.out.println();
        return this;
    }

    @Override
    public void demo() {
        new StateApp()
            .testState()
            ;
    }
}
