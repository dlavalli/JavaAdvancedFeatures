package com.lavalliere.daniel.projects.patterns.behavioral.template;

public class UnsubscribeEmail extends Email {

    public void sendMessageBody() {
        System.out.println("We are sorry to see you go." );
    }
}