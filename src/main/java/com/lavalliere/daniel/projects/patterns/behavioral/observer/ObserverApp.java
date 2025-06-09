package com.lavalliere.daniel.projects.patterns.behavioral.observer;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class ObserverApp implements Demoable {

    private ObserverApp observeIt() {
        var user1 = new User();
        var user2 = new User();
        var newsfeed = new Newsfeed();

        // Register both users to listen for news feeds status changes
        user1.addPropertyChangeListener(newsfeed);
        user2.addPropertyChangeListener(newsfeed);

        user1.setStatus("Going for a walk");
        user2.setStatus("Enjoying a coffee");

        newsfeed.printStatuses();
        System.out.println();
        System.out.println();
        return this;
    }

    @Override
    public void demo() {
        new ObserverApp()
            .observeIt()
            ;
    }
}
