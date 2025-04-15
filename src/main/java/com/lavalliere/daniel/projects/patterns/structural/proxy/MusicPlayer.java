package com.lavalliere.daniel.projects.patterns.structural.proxy;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class MusicPlayer implements Demoable {

    private static final RecommendationsProxy recommendationsProxy = new RecommendationsProxy();

    private static void loadHomePage(User user) {
        System.out.println("Loading home page...");
        recommendationsProxy.showRecommendations(user);
    }

    private static void loadDiscoverPage(User user) {
        System.out.println("Loading discover page...");
        recommendationsProxy.showRecommendations(user);
    }

    private MusicPlayer loadIt() {
        var jill = new User("Jill", false);
        loadHomePage(jill);
        loadDiscoverPage(jill);

        var paul = new User("Paul", true);
        loadHomePage(paul);
        loadDiscoverPage(paul);

        System.out.println();
        return this;
    }

    public void demo() {
        loadIt();
    }
}
