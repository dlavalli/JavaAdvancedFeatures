package com.lavalliere.daniel.projects.patterns.structural.composite;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class CompositeApp implements Demoable {

    private CompositeApp composeIt() {
        Checklist checklist = new Checklist();
        var todoItem1 = new TodoItem("Do this");
        var todoItem2 = new TodoItem("Do that");
        var todoItem3 = new TodoItem("Do something else");

        checklist.addTodoItem(todoItem1);
        checklist.addTodoItem(todoItem2);
        checklist.addTodoItem(todoItem3);

        todoItem1.complete();

        checklist.isCompleted();
        System.out.println();
        return this;
    }

    public void demo() {
        composeIt();
    }
}
