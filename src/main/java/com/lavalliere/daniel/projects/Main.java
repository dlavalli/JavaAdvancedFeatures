package com.lavalliere.daniel.projects;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.utils.AnnotationsScanner;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

public class Main {

    public static void main(String[] args) {
        AnnotationsScanner.scan(
            IsDemoable.class,
            Main.class.getPackageName(),
            (bean) -> {
              if (bean instanceof Demoable demoable) {
                   demoable.demo();
              }
            });
    }
}