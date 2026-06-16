package com.lavalliere.daniel.projects.executors;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;
import com.lavalliere.daniel.projects.streams.CommonExamples;

@IsDemoable
public class Main implements Demoable {

//    public Main doNumberEvenOrOdd() {
//        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
//        numbers.forEach(NumberUtils::evenOrOdd);  // Calling a function reference
//        return this;
//    }

    @Override
    public void demo() {
        new CommonExamples()
        // .doNumberEvenOrOdd()
        ;
    }
}
