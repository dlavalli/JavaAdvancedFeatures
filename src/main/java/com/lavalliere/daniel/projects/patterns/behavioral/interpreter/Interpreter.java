package com.lavalliere.daniel.projects.patterns.behavioral.interpreter;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class Interpreter implements Demoable {

    private Interpreter doInterpret() {
        var context = "_My Variable";
        var firstLetterShouldNotBeUnderscore = new FirstLetterShouldNotBeUnderscore();
        var result = firstLetterShouldNotBeUnderscore.interpret(context);
        System.out.println(result);
        System.out.println();
        return this;
    }

    @Override
    public void demo() {
        new Interpreter()
            .doInterpret()
            ;
    }
}
