package com.lavalliere.daniel.projects.patterns.structural.bridge;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class BridgeApp implements Demoable {

    private BridgeApp bridgeIt() {
        var hoodie = new Hoodie(new ChildSize());
        hoodie.getType();
        hoodie.getSize().get();

        var shirt = new Shirt(new AdultSize());
        shirt.getType();
        shirt.getSize().get();

        System.out.println();
        return this;
    }

    public void demo() {
        bridgeIt();
    }
}
