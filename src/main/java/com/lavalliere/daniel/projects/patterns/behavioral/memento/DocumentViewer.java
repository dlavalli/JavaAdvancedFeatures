package com.lavalliere.daniel.projects.patterns.behavioral.memento;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class DocumentViewer implements Demoable {

    private DocumentViewer testStateChanges() {
        var textBlock = new TextDocument();
        textBlock.write("Hello, world! ");
        textBlock.save();
        textBlock.print();
        textBlock.write("How are you? ");
        textBlock.print();
        textBlock.undo();
        textBlock.print();
        System.out.println();
        System.out.println();
        return this;
    }

    @Override
    public void demo() {
        new DocumentViewer()
            .testStateChanges()
            ;
    }
}
