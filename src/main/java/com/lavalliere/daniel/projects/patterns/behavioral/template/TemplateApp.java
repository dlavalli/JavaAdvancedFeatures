package com.lavalliere.daniel.projects.patterns.behavioral.template;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class TemplateApp implements Demoable {

    private TemplateApp testTemplate() {
        var welcomeEmail = new WelcomeEmail();
        welcomeEmail.sendGreeting();
        welcomeEmail.sendMessageBody();
        welcomeEmail.sendClosing();

        var unsubscribeEmail = new UnsubscribeEmail();
        unsubscribeEmail.sendGreeting();
        unsubscribeEmail.sendMessageBody();
        unsubscribeEmail.sendClosing();
        System.out.println();
        return this;
    }

    @Override
    public void demo() {
        new TemplateApp()
            .testTemplate()
            ;
    }
}
