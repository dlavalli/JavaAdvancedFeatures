package com.lavalliere.daniel.projects.core;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class Main implements Demoable {

    public Main doTestIncrementDecrement() {
        int x = 2;
        // Basically ++/-- are applied after first assigning x
        // ie: x = x - (x++ + x--)
        //         2 -  (2 -> 3 + 3 ->2)
        x -= (x++ + x--);
        System.out.println("x -= (x++ + x--) equals " + x);
        return this;
    }

    @Override
    public void demo() {
        new Main()
            .doTestIncrementDecrement();
    }
}
