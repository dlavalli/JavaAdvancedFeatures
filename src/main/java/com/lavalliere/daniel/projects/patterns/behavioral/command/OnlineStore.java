package com.lavalliere.daniel.projects.patterns.behavioral.command;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class OnlineStore  implements Demoable {

    public OnlineStore orderIt() {
        var item1 = new Item(1);
        var item2 = new Item(2);
        var item3 = new Item(3);

        var itemOperationExecutor = new ItemOperationExecutor();
        itemOperationExecutor.queueOperation(new AddItemToBasketOperation(item1));
        itemOperationExecutor.queueOperation(new AddItemToBasketOperation(item2));
        itemOperationExecutor.queueOperation(new AddItemToBasketOperation(item3));
        itemOperationExecutor.queueOperation(new RemoveItemFromBasketOperation(item2));

        itemOperationExecutor.checkout();
        System.out.println();
        return this;
    }

    public void demo() {
        orderIt();
    }
}
