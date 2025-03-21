package com.lavalliere.daniel.projects.patterns.creational.builder;

import java.time.LocalDate;

public class Person

{
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final String emailAddress;
    private final String phoneNumber;

    // Pass in the builder to avoid having a constructor with many possible arguments
    private Person(PersonBuilder personBuilder) {
        this.firstName = personBuilder.firstName;
        this.middleName = personBuilder.middleName;
        this.lastName = personBuilder.lastName;
        this.dateOfBirth = personBuilder.dateOfBirth;
        this.emailAddress = personBuilder.emailAddress;
        this.phoneNumber = personBuilder.phoneNumber;
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("First name and last name must not be null");
        }
    }

    @Override
    public String toString() {
        return "Person{" +
            "firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", dateOfBirth=" + dateOfBirth +
            ", emailAddress='" + emailAddress + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            '}';
    }

    // Put builder here to ensure that the PersonBuilder can only be used when Person objects are created
    public static class PersonBuilder {

        private final String firstName;
        private String middleName;
        private final String lastName;
        private LocalDate dateOfBirth;
        private String emailAddress;
        private String phoneNumber;

        // Constructor should only contain the mandatory fields
        public PersonBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public PersonBuilder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public PersonBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public PersonBuilder emailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public PersonBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Person build() {
            return new Person(this);
        }


    }

}