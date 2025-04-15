package com.lavalliere.daniel.projects.patterns.creational.builder;


import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

import java.time.LocalDate;

@IsDemoable
public class SalesLeadTracker implements Demoable {

    private SalesLeadTracker testAddPerson() {
        var person1 = new Person.PersonBuilder("Tracy", "Westbay")
            .dateOfBirth(LocalDate.of(1985, 1, 1))
            .emailAddress("tracy@example.com")
            .build();

        addPersonToLeadTracker(person1);

        var person2 = new Person.PersonBuilder("Jerome", "Donaldson")
            .middleName("Henry")
            .phoneNumber("123456789")
            .build();

        addPersonToLeadTracker(person2);
        return this;
    }

    private void addPersonToLeadTracker(Person person) {
        System.out.println("A new lead was added to the lead tracker: \n" + person);
        System.out.println();
    }

    public void demo() {
        testAddPerson();
    }
}