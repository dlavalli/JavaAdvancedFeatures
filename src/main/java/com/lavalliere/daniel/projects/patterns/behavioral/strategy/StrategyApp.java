package com.lavalliere.daniel.projects.patterns.behavioral.strategy;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class StrategyApp implements Demoable {

    private StrategyApp testStrategy() {
        var loginPage = new LoginPage();
        loginPage.logInUser(Authenticator.passwordAuthenticator);
        loginPage.logInUser(Authenticator.singleSignOnAuthenticator);
        System.out.println();
        System.out.println();
        return this;
    }

    @Override
    public void demo() {
        new StrategyApp()
            .testStrategy()
            ;
    }
}
