package com.lavalliere.daniel.projects.patterns.behavioral.iterator;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class IteratorApp  implements Demoable {

    private IteratorApp doIterate() {
        var pen = new Item("Pen", 20);
        var pencil = new Item("Pencil", 0);
        var eraser = new Item("Eraser", 15);

        var inventory = new Inventory(pen, pencil, eraser);
        var stockIterator = inventory.iterator();

        while (stockIterator.hasNext()) {
            var item = stockIterator.next();
            System.out.println(item.getName());
        }
        System.out.println();
        return this;
    }


    @Override
    public void demo() {
        new IteratorApp()
            .doIterate()
            ;
    }
}
