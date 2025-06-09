package com.lavalliere.daniel.projects.patterns.behavioral.template;

public class WelcomeEmail extends Email {

    public void sendMessageBody() {
        System.out.println("Thank you for signing up for our service.");
    }
}
