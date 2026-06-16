package com.lavalliere.daniel.projects.streams;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.IntStream;

@IsDemoable
public class OptionalExamples implements Demoable {

    public OptionalExamples sumListElementsOr0OnEmpty() {
        System.out.println("sumListElementsOr0OnEmpty");
        List<Integer> ints = List.of(1,2,3,4,5);
        // List<Integer> ints = List.of();
        int sum = ints.stream()
            // .reduce(0, Integer::sum);
            .collect(Collectors.summingInt(Integer::intValue));
        System.out.println("sum = " + sum);
        return this;
    }

    public OptionalExamples getFirstListElementSafely() {
        System.out.println("getFirstListElementSafely");
        // String match = Stream.of()
        String match = Stream.of("apple", "banana", "cherry")
            .findFirst()
            .orElse("No element found").toString();   // toString() Requires else returns Object instead of String
                                                            // NOTE: .orElse is part of Optional() not Stream/Collectors
        System.out.println("match = " + match);
        return this;
    }

    public OptionalExamples throwIfNoMatchingElementFound() {
        System.out.println("throwIfNoMatchingElementFound");
        int match = IntStream.of(1,3,5,7,9,10) // ,10
            .filter(n -> n % 2 == 0)
            .findFirst().orElseThrow(NoSuchElementException::new);
        System.out.println("match = " + match);
        return this;
    }

    public OptionalExamples nameOfHighestPaidOrNotFoundMessage() {
        System.out.println("getFirstListElement");
        List<CommonExamples.Employee> employee = List.of(
            new CommonExamples.Employee("Alice", "Accounting", 50000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Bob", "IT", 60000, LocalDate.of(2026,1,1), 3),
            new CommonExamples.Employee("Charlie", "HR", 45000, LocalDate.of(2026,1,1), 3),
            new CommonExamples.Employee("David", "HR", 55000, LocalDate.of(2026,1,1), 3),
            new CommonExamples.Employee("John", "IT", 66000, LocalDate.of(2026,1,1), 3)
        );
        String match = employee.stream()
            .max(Comparator.comparing(CommonExamples.Employee::salary)) // Returns an Optional<Employee>
                                                                        // .orElse(null); is from Optional<Employee> so cannot return a message
            .map(CommonExamples.Employee::name).orElse("Not Found");  // NOTE: from Optional<Employee> NOT stream final operation
            // .collect(Collectors.maxBy(Comparator.comparing(CommonExamples.Employee::salary))).orElse(null);  // Returns an Optional and .orElse([instance of supplier])
        System.out.println("match = " + match);
        return this;
    }

    @Override
    public void demo() {
        new OptionalExamples()
            .sumListElementsOr0OnEmpty()
            .getFirstListElementSafely()
            .throwIfNoMatchingElementFound()
            .nameOfHighestPaidOrNotFoundMessage()
        ;
    }
}
