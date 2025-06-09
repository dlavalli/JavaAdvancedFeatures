package com.lavalliere.daniel.projects.patterns.behavioral.command;

import java.util.ArrayList;
import java.util.List;

public class ItemOperationExecutor {

    List<ItemOperation> itemOperations = new ArrayList<>();

    public void queueOperation(ItemOperation itemOperation) {
        itemOperations.add(itemOperation);
    }

    public void checkout() {
        System.out.println("About to perform checkout");
        itemOperations.forEach(ItemOperation::execute);
        itemOperations.clear();
    }



}