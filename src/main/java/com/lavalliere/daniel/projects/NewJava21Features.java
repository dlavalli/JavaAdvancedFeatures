package com.lavalliere.daniel.projects;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

import static java.util.FormatProcessor.FMT;

public class NewJava21Features {

    public record Product(String id, String name, double price) {}


    public NewJava21Features showStringTemplates() {
        var product = new Product("bogusId", "bogusName", 2.0);

        // STR is statically imported implicitly into every Java compilation unit.
        String string4 = STR."Products with id \{ product.id() } is \{ product.name() } and has price $\{ product.price()  }";

        // Unlike STR, FMT/RAW must be statically imported explicitly.
        String string5 = FMT."Products with id \{ product.id() } is \{ product.name() } and has price $%.2f\{ product.price()  }";
        System.out.println(string4);
        System.out.println(string5);

        return this;
    }

    public NewJava21Features showOldCollections() {
        // Old way
        var arrayList = new ArrayList<>(Arrays.asList("Banana", "Cherry", "Date"));
        arrayList.add(0, "Apple");
        arrayList.add(arrayList.size(), "Elderberry");
        System.out.println(arrayList);
        System.out.println(arrayList.get(0));
        System.out.println(arrayList.get(arrayList.size()-1));


        var deque = new ArrayDeque<>(Arrays.asList("Banana", "Cherry", "Date"));
        deque.addFirst("Apple");
        deque.addLast("Elderberry");
        System.out.println(deque);
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());

        var linkedHashSet = new LinkedHashSet<>(Arrays.asList("Banana", "Cherry", "Date"));
        linkedHashSet.add("Apple");
        System.out.println(linkedHashSet);
        System.out.println(linkedHashSet.iterator().next());
        System.out.println(linkedHashSet.toArray()[linkedHashSet.size() -1]);
        return this;
    }

    public NewJava21Features showSequencedCollections() {
        // Old way
        var arrayList = new ArrayList<>(Arrays.asList("Banana", "Cherry", "Date"));
        arrayList.addFirst("Apple");
        arrayList.addLast("Elderberry");
        System.out.println(arrayList);
        System.out.println(arrayList.getFirst());
        System.out.println(arrayList.getLast());


        var deque = new ArrayDeque<>(Arrays.asList("Banana", "Cherry", "Date"));
        deque.addFirst("Apple");
        deque.addLast("Elderberry");
        System.out.println(deque);
        System.out.println(deque.getFirst());
        System.out.println(deque.getLast());

        var linkedHashSet = new LinkedHashSet<>(Arrays.asList("Banana", "Cherry", "Date"));
        linkedHashSet.addFirst("Apple");
        linkedHashSet.addLast("Elderberry");
        System.out.println(linkedHashSet);
        System.out.println(linkedHashSet.getFirst());
        System.out.println(linkedHashSet.getLast());
        return this;
    }

    public static void demo() {
        new NewJava21Features()
                .showStringTemplates()
                .showOldCollections()
                .showSequencedCollections()

        ;
    }
}
