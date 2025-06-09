package com.lavalliere.daniel.projects.patterns.behavioral.strategy;

public class LoginPage {
    public void logInUser(Authenticator authenticator) {
        authenticator.logIn();
    }

}