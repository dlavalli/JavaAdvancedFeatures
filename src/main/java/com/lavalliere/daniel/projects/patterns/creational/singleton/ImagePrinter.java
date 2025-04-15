package com.lavalliere.daniel.projects.patterns.creational.singleton;


import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class ImagePrinter implements Demoable {

    public ImagePrinter printImage() {
        var spooler = PrintSpooler.getInstance();
        spooler.print();
        var spooler2 = PrintSpoolerEnum.getInstance();
        spooler2.print();
        System.out.println();
        return this;
    }

    public void demo() {
        printImage();
    }
}