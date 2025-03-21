package com.lavalliere.daniel.projects.patterns.creational.singleton;


public class ImagePrinter {

    public ImagePrinter printImage() {
        var spooler = PrintSpooler.getInstance();
        spooler.print();
        var spooler2 = PrintSpoolerEnum.getInstance();
        spooler2.print();
        System.out.println();
        return this;
    }

    public static void demo() {
        new ImagePrinter()
            .printImage()
        ;
    }

}