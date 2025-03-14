package com.lavalliere.daniel.projects.ocaocr.lambdas.lab;

import java.util.*;
import java.util.function.*;

public class LambdasAndMethodReferences {

    private void staticMR() {
        // NOTE - List.of(...) DOES NOT support Collections.sort (NOT Comparable)
        List<Integer> integers = Arrays.asList(1, 2, 7, 4, 5);
        Consumer<List<Integer>> consumerL = list -> Collections.sort(list);
        consumerL.accept(integers);
        System.out.println(integers);

        integers = Arrays.asList(1, 2, 7, 4, 5);

        // NOTE the version of sort selecte by the Method reference is based on the arguments at calling type
        //      in this case, no Comparator interface implementation is provided hence the sort version with single argument is selected
        //      wich sill sort based on value
        Consumer<List<Integer>> consumerMR = Collections::sort;
        consumerMR.accept(integers);
        System.out.println(integers);
    }

    private void boundMR() {
        String name = "Mr. Joe Bloggs";
        Predicate<String> predicateL = prefix -> name.startsWith(prefix);
        System.out.println(String.format("%s startWith: %s: %b", name,"Mr.",predicateL.test("Mr.")));
        System.out.println(String.format("%s startWith: %s: %b", name,"Ms.",predicateL.test("Ms.")));

        Predicate<String> predicateMR = name::startsWith;
        System.out.println(String.format("%s startWith: %s: %b", name,"Mr.",predicateMR.test("Mr.")));
        System.out.println(String.format("%s startWith: %s: %b", name,"Ms.",predicateMR.test("Ms.")));
    }

    private void unboundMR() {
        Predicate<String> predicateL = str -> str.isEmpty();
        System.out.println(String.format("%s isEmpty: %b", "",predicateL.test("")));
        System.out.println(String.format("%s isEmpty: %b", "xyz",predicateL.test("xyz")));

        Predicate<String> predicateMR = String::isEmpty;
        System.out.println(String.format("%s isEmpty: %b", "",predicateMR.test("")));
        System.out.println(String.format("%s isEmpty: %b", "xyz",predicateMR.test("xyz")));

        BiPredicate<String, String> biPredicateL = (first, second) -> first.startsWith(second);
        System.out.println(String.format("%s startsWith: %s %b", "Mr. Joe Bloggs", "Mr.", biPredicateL.test("Mr. Joe Bloggs", "Mr.")));
        System.out.println(String.format("%s startsWith: %s %b", "Mr. Joe Bloggs", "Ms.", biPredicateL.test("Mr. Joe Bloggs", "Ms.")));

        BiPredicate<String, String> biPredicateMR = String::startsWith;
        System.out.println(String.format("%s startsWith: %s %b", "Mr. Joe Bloggs", "Mr.", biPredicateMR.test("Mr. Joe Bloggs", "Mr.")));
        System.out.println(String.format("%s startsWith: %s %b", "Mr. Joe Bloggs", "Ms.", biPredicateMR.test("Mr. Joe Bloggs", "Ms.")));
    }

    private void constructorMR() {
        Supplier<List<String>> supplierL = () -> new ArrayList<String>();
        List<String> list = supplierL.get();
        list.add("Lambda");
        System.out.println(list);

        Supplier<List<String>> supplierMR = ArrayList::new;
        list = supplierMR.get();
        list.add("Method Reference");
        System.out.println(list);

        Function<Integer, ArrayList<String>> functionL = size -> new ArrayList<String>(size);
        ArrayList<String> list2 = functionL.apply(10);
        System.out.println(list2);
        list2.add("Lambda");
        System.out.println(list2);

        Function<Integer, ArrayList<String>> functionMR = ArrayList::new;
        list2 = functionMR.apply(10);
        System.out.println(list2);
        list2.add("Lambda");
        System.out.println(list2);
    }

    public static void main(String[] args) {
        LambdasAndMethodReferences lambdas = new LambdasAndMethodReferences();
        lambdas.staticMR();
        lambdas.boundMR();
        lambdas.unboundMR();
        lambdas.constructorMR();
    }
}
