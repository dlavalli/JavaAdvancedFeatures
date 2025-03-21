package com.lavalliere.daniel.projects;

import com.lavalliere.daniel.projects.ocaocr.streams.Java8Features;
import com.lavalliere.daniel.projects.patterns.creational.builder.SalesLeadTracker;
import com.lavalliere.daniel.projects.patterns.creational.singleton.ImagePrinter;
import com.lavalliere.daniel.projects.patterns.creational.prototype.Cityscape;
import com.lavalliere.daniel.projects.patterns.creational.factorymethod.TicketOffice;
import com.lavalliere.daniel.projects.patterns.creational.abstractfactories.UserInterfaceCreator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        NewJava21Features.demo();
        NewJavaTime.demo();
        Java8Features.demo();
        SalesLeadTracker.demo();
        ImagePrinter.demo();
        Cityscape.demo();
        TicketOffice.demo();
        UserInterfaceCreator.demo();
    }
}