package com.lavalliere.daniel.projects.patterns.structural.flyweight;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class Restaurant implements Demoable {

    private Restaurant orderIt() {
        var pizza1 = OrderFactory.createOrder("Pizza");
        var pizza2 = OrderFactory.createOrder("Pizza");
        var pizza3 = OrderFactory.createOrder("Pizza");
        System.out.println(pizza1);
        System.out.println(pizza2);
        System.out.println(pizza3);

        var burger1 = OrderFactory.createOrder("Burger");
        var burger2 = OrderFactory.createOrder("Burger");
        var burger3 = OrderFactory.createOrder("Burger");
        System.out.println(burger1);
        System.out.println(burger2);
        System.out.println(burger3);

        System.out.println();

        return this;
    }

    public void demo() {
        orderIt();
    }
}
