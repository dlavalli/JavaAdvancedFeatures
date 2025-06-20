package com.lavalliere.daniel.projects.patterns.behavioral.visitor;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class VisitorApp implements Demoable {

    private VisitorApp doVisit() {
        var directory1 = new Directory("Directory1");
        var file1 = new File("file1.txt", 100);
        directory1.addElement(file1);

        var directory2 = new Directory("Directory2");
        var file2 = new File("file2.txt", 200);
        directory2.addElement(file2);

        directory1.addElement(directory2);

        var visitor = new CalculateSizeVisitor();
        directory1.accept(visitor);

        System.out.println("Total size: " + visitor.getTotalSize());
        System.out.println();
        return this;
    }

    @Override
    public void demo() {
        new VisitorApp()
            .doVisit()
            ;
    }
}
