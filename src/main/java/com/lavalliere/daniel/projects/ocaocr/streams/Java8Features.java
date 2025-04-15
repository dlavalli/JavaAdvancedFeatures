package com.lavalliere.daniel.projects.ocaocr.streams;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@IsDemoable
public class Java8Features implements Demoable {
    AtomicInteger ai1 = new AtomicInteger();
    AtomicInteger ai2 = new AtomicInteger();

    private Java8Features intAvg() {
        OptionalDouble average = IntStream.range(0, 5).average();
        System.out.printf("The average is: %f\n",average.isPresent() ? average.getAsDouble() : 0.0);
        return this;
    }

    private Java8Features itemList() {
        List<Item> items = List.of(
                new Item(1, "Screw"),
                new Item(2, "Nail"),
                new Item(3, "Bolt")
        );
        items.stream().sorted(Comparator.comparing(Item::getName)).forEach(System.out::print);
        System.out.println();
        return this;
    }

    private Java8Features flatMap() {
        Stream<List<String>> streams = Stream.of(
                Arrays.asList("a", "b"),
                Arrays.asList("a", "c")
        );
        streams
            .filter(str -> str.contains("c"))
            .flatMap(list -> list.stream())
            .forEach(System.out::print);
        System.out.println();
        return this;
    }

    private Java8Features intList() {
        int sum = IntStream.range(0, 5).sum();
        int min = IntStream.range(0, 5).min().getAsInt();
        int max = IntStream.range(0, 5).max().getAsInt();
        System.out.printf("sum: %d min: %d max: %d", sum, min, max);

        List<Person> persons = List.of(
          new Person("Alan", "Burke", 22),
          new Person("Zoe", "Peters", 20),
          new Person("Peter", "Castle", 29)
        );
        int oldest = persons.stream().max(Comparator.comparing(Person::getAge)).get().getAge();
        System.out.printf("Oldest: %d\n", oldest);
        List<Integer> nums = List.of(10, 47, 33, 23);
        Optional<Integer> max1 = nums.stream().reduce((a, b) -> a > b ? a : b);
        Integer max2 = nums.stream().reduce(0, (a, b) -> a > b ? a : b);
        System.out.printf("max1: %d max2: %d\n",max1.get(),max2);
        return this;
    }

    public static Optional<String> getGrade(int marks) {
        Optional<String> grade = Optional.empty();
        if (marks > 50){
            grade = Optional.of("PASS");
        } else {
            grade.of("Fail");
        }
        return grade;
    }

    private Java8Features filterBooks() {
        List<Book> books = List.of(
          new Book("Atlas Shrugged", 10.0),
          new Book("Freedom at Midnight", 5.0),
          new Book("Gone with the wind", 5.0)
        );
        Map<String, Double> booksByTitles = books.stream().collect(Collectors.toMap(
                Book::getTitle,
                Book::getPrice
           )
        );
        booksByTitles.forEach(
            (key, value) -> {
                if (key.startsWith("A")) System.out.printf("Title: %s Price: %f\n",key, value.doubleValue());
            }
        );
        System.out.println();
        return this;
    }

    private Java8Features filterDuplicateBooks() {
        List<Book> books = List.of(
            new Book("Gone with the wind", 5.0),
            new Book("Gone with the wind", 10.0),
            new Book("Atlas Shrugged", 15.0)
        );
        books.stream()
            .collect(
                Collectors.toMap(
                    Book::getTitle,
                    Book::getPrice,
                    // (existing, replacement) -> existing + replacement
                    BinaryOperator.maxBy(Comparator.comparing((price -> price)))
                )
            )
            .forEach((title, cost) -> System.out.printf("title: %s cost: %f\n",title,cost.doubleValue()));
        return this;
    }

    private Java8Features avgFilteredElements() {
        List<Person> persons = List.of(
            new Person("Bob", "Burke", 31),
            new Person("Paul", "Peters  ", 32),
            new Person("John", "Johnson", 33)
        );
        double avg = persons.stream()
            .filter(person -> person.getAge() < 30)
            .mapToDouble(Person::getAge)
            .average()
            .orElse(0.0);
        System.out.printf("Average: %f\n",avg);
        System.out.println();
        return this;
    }

    private Java8Features checkOptional() {
        Optional<Double> price = Optional.ofNullable(20.0);
        price.ifPresent(System.out::println);
        System.out.println(price.orElse(0.0));
        System.out.println(price.orElseGet(() -> 0.0));

        Optional<Double> price2 = Optional.ofNullable(null);
        System.out.println(price2);
        System.out.printf("%s\n",price2.isEmpty() ? "empty" : "");
        price2.ifPresent(System.out::println);
        Double x = price2.orElse(44.0);
        System.out.println(x);

        Optional<Double> price3 = Optional.ofNullable(null);
        try {
            Double z = price3.orElseThrow(() -> new RuntimeException("Bad Code"));
            System.out.println(price3);
        }catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        return this;
    }

    private Java8Features dumpAnotherBookList() {
        List<AnotherBook> books = List.of(
          new AnotherBook("Gone with the wind", "Fiction"),
          new AnotherBook("Bourne Ultimatum", "Thriller"),
          new AnotherBook("The Client", "Thriller")
        );
        List<String> genres = new ArrayList<>();
        genres = books.stream()
            .map(AnotherBook::getGenre)
            .distinct()
            .collect(Collectors.toList());

        System.out.println(genres);
        System.out.println();
        return this;
    }

    private Java8Features testDoubleStreams() {
        DoubleStream doubleStream = DoubleStream.of(0, 2, 4);
        System.out.println(doubleStream.filter(n -> n % 2 != 0).sum());
        doubleStream = DoubleStream.of(1.0, 3.0);
        System.out.println(doubleStream.filter(n -> n % 2 == 0).average().orElse(0.0));
        System.out.println();
        return this;
    }

    private Java8Features testLazy() {
        List<Integer> ls = List.of(11,11,22,33,33,55,66);
        System.out.printf("anyMatch for 11: %b\n", ls.stream().distinct().anyMatch(num -> num == 11));
        ls = List.of(11,11,22,33,33,55,66);
        System.out.printf("nonMatch for x %% 11: %b\n", ls.stream().distinct().noneMatch(x -> x % 11 > 0));
        System.out.println();
        return this;
    }

    private Java8Features testParallel1() {
        Stream.of(11,11,22,33)
            .parallel()
            .filter(n -> {
                    ai1.incrementAndGet();
                    return n % 2 == 0;
                }
            );  // This always return 0 as there is no terminal operation hence nothing occured when statement is completed
                // adding a termination statement cause the incrementer to be 4 as it was visisted for 4 values and prints 22 which is even
            // .forEach(System.out::println);
        System.out.println(ai1);
        System.out.println("======");

        Stream<Integer> stream =  Stream.of(11,11,22,33).parallel();
        stream
            .filter(n -> {
                    ai2.incrementAndGet();
                    return n % 2 == 0;
                }
            ).forEach(System.out::println);     // Resolves the exception below
        // stream.forEach(System.out::println); // Stream has already been linked or consumed
        System.out.println(ai2);
        System.out.println();
        return this;
    }

    public void demo() {
        this.intAvg()
            .itemList()
            .flatMap()
            .intList()
            ;

        Optional<String> grade1 = getGrade(50);
        Optional<String> grade2 = getGrade(55);
        System.out.printf("grade1: %s\n",grade1.orElse("UNKNOWN"));

        if (grade2.isPresent()) grade2.ifPresent(System.out::println);
        else System.out.printf("%s\n",grade2.orElse("Empty") );

        this.filterBooks()
            .filterDuplicateBooks()
            .avgFilteredElements()
            .checkOptional()
            .dumpAnotherBookList()
            .testDoubleStreams()
            .testLazy()
            .testParallel1()
        ;
    }
}
