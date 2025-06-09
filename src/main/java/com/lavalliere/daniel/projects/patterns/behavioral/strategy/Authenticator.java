package com.lavalliere.daniel.projects.patterns.behavioral.strategy;

public interface Authenticator {
    void logIn();

    // 2 Implementations of the interface with function ref
    Authenticator passwordAuthenticator = () -> System.out.println("Logging in with username and password.");
    Authenticator singleSignOnAuthenticator = () -> System.out.println("Logging in with single sign-on.");
}