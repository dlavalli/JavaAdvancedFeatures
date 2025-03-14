package com.lavalliere.daniel.projects.ocaocr.lambdas.lab;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
public class BasicLambdas {

    // Udemy course material
    // https://github.com/Sean-D-Kennedy/AdvancedJavaProgramming

    private static List<Person> getPeople() {
        List<Person> result = new ArrayList<>();
        result.add(new Person("Mike", 33, 1.8));
        result.add(new Person("Mary", 25, 1.4));
        result.add(new Person("Alan", 34, 1.7));
        result.add(new Person("Zoe", 30, 1.5));
        return result;
    }

    private void printPerson(Person person) {
        System.out.println(String.format("Name: %s, Age: %d, Height: %f",
                person.getName(),
                person.getAge(),
                person.getHeight())
        );
    }

    private void sortName(List<Person> listPerson) {
        listPerson.sort(Comparator.comparing(Person::getName));
        // listPerson.forEach(person -> printPerson(person));
        listPerson.forEach(this::printPerson);
    }

    private void sortAge(List<Person> listPerson) {
        listPerson.sort(Comparator.comparing(Person::getAge));
        // listPerson.forEach(person -> printPerson(person));
        listPerson.forEach(this::printPerson);
    }

    private void sortHeight(List<Person> listPerson) {
        listPerson.sort(Comparator.comparing(Person::getHeight));
        // listPerson.forEach(person -> printPerson(person));
        listPerson.forEach(this::printPerson);
    }

    private void consumer() {
        String msg = "Printable lambda";
        Printable<String> printable = data -> System.out.println(data);
        printable.print(msg);

        Consumer<String> consumerL = data -> System.out.println(data);
        Consumer<String> consumerMR = System.out::println;
        consumerL.accept(msg);
        consumerMR.accept(msg);
    }

    private void supplier() {
        Retrievable<Integer> retrievable = () -> 77;
        System.out.println(retrievable.retrieve());
        Supplier<Integer> supplier = () -> 77;
        System.out.println(supplier.get());
    }

    private void predicate() {
        Evaluate<Integer> evaluate = num -> num < 0;
        System.out.println(String.format("%d < 0 : %b", -1, evaluate.isNegative(-1)));
        System.out.println(String.format("%d < 0 : %b", 1, evaluate.isNegative(1)));
        Predicate<Integer> predicate = num -> num < 0;
    }

    private <T> boolean check(T t, Predicate<T> predicate) {
        return predicate.test(t);
    }

    private void function() {
        Functionable<Integer, String> concatFunctionable = num -> "Number is: " + num;
        System.out.println(concatFunctionable.applyThis(25));

        Function<Integer, String> concatFunction = num -> "Number is: " + num;
        System.out.println(concatFunction.apply(25));
    }

    public static void main(String[] args) {
        BasicLambdas lambdas = new BasicLambdas();
        lambdas.consumer();
        lambdas.supplier();
        lambdas.predicate();

        System.out.println(String.format("%d is even: %b",4, lambdas.check(4, num -> num % 2 == 0)));
        System.out.println(String.format("%d is even: %b",7, lambdas.check(7, num -> num % 2 == 0)));
        System.out.println(String.format("%s startsWith Mr.: %b","Mr. Joe Bloggs", lambdas.check("Mr. Joe Bloggs", str -> str.startsWith("Mr."))));
        System.out.println(String.format("%s startsWith Mr.: %b","Ms. Ann Bloggs", lambdas.check("Ms. Ann Bloggs", str -> str.startsWith("Mr."))));

        Person mike = new Person("Mike", 33,1.8);
        Person ann = new Person("Ann", 13,1.4);
        System.out.println(String.format("%s %d >= 18: %b", mike.getName(), mike.getAge(), lambdas.check(mike, person -> person.getAge() >= 18)));
        System.out.println(String.format("%s %d >= 18: %b", ann.getName(), ann.getAge(), lambdas.check(ann, person -> person.getAge() >= 18)));

        lambdas.function();

        List<Person> listPeople = getPeople();
        lambdas.sortAge(listPeople);
        lambdas.sortName(listPeople);
        lambdas.sortHeight(listPeople);
    }
}
