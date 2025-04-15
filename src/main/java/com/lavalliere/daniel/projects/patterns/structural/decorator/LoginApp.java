package com.lavalliere.daniel.projects.patterns.structural.decorator;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class LoginApp implements Demoable {

    private LoginApp login() {
        var user = new BasicUser();
        user.printPrivileges();

        System.out.println();

        var adminUser = new AdminUser(user);
        adminUser.printPrivileges();

        System.out.println();

        var developerUser = new DeveloperUser(adminUser);
        developerUser.printPrivileges();

        System.out.println();
        return this;
    }

    public void demo() {
        login();
    }
}
