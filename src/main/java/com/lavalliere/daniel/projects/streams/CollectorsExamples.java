package com.lavalliere.daniel.projects.streams;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@IsDemoable
public class CollectorsExamples implements Demoable {

    public static boolean isPrimeNumber(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public CollectorsExamples findAverageSalaryWithCollect() {
        System.out.println("findAverageSallaryWithCollect");
        Double avrSalary = Stream.of(
            new CommonExamples.Employee("Alice", "Accounting", 50000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Bob", "IT", 60000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Charlie", "HR", 45000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("David", "Development", 55000, LocalDate.of(2026,1,1), 30)
        ).collect(Collectors.averagingDouble(CommonExamples.Employee::salary));
        System.out.println("avrSalary: " + avrSalary);
        return this;
    }

    public CollectorsExamples sumAllOddNumbers() {
        System.out.println("sumAllOddNumbers");
        int sum = IntStream.of(1,2,3,4,5,6,7,8,9,10)
            .filter(number -> number % 2 != 0)
            .boxed()
            .collect(Collectors.summingInt(Integer::intValue));
        System.out.println("sum: " + sum);
        return this;
    }

    public CollectorsExamples findMostFrequentlyOccurringCharacterInString() {
        System.out.println("findMostFrequentlyOccurringCharacterInString");
        Map.Entry<Character, Long> match = "hello worldo".chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(Character::charValue, Collectors.counting()))
            .entrySet().stream() // Create a new entry stream for the returned Map.Entry<Character, Long> match

            // First version uses method for map entry to compare instead of Comparing
            .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
            .findFirst()
            .orElse(null);  // NOTE: .orElse is part of Optional() not Stream/Collectors

            // Second version stating the type of the element
            // .sorted(Comparator.<Map.Entry<Character, Long>, Long>comparing(Map.Entry::getValue).reversed())
            // .findFirst()
            // .orElse(null);  // NOTE: .orElse is part of Optional() not Stream/Collectors

            // Also works but returns the last occurrence !!!
            // .sorted(Comparator.comparing(Map.Entry::getValue))   // get not add .reversed(), get wrong type
            // .collect(Collectors.reducing((first, second) -> second))
            // .orElse(null);  // NOTE: .orElse is part of Optional() not Stream/Collectors
        System.out.println("Largest number of occurrence: " + match);
        return this;
    }

    public CollectorsExamples groupEmployeeListByDepartment() {
        System.out.println("groupEmployeeListByDepartment");
        Map<String, List<CommonExamples.Employee>> employees = Stream.of(
            new CommonExamples.Employee("Alice", "Accounting", 50000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Bob", "IT", 60000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Charlie", "HR", 45000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("David", "HR", 55000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("John", "IT", 66000, LocalDate.of(2026,1,1), 30)
        ).collect(Collectors.groupingBy(CommonExamples.Employee::department));
        System.out.println("employees by department: " + employees);
        return this;
    }

    public CollectorsExamples partitionNumbersListIntoPrimeandNonPrime() {
        System.out.println("partitionNumbersListIntoPrimeandNonPrime");
        Map<Boolean, List<Integer>> primeNumbers = IntStream.of(2,3,4,5,6,7,8,9,10)
            .boxed()
            .collect(Collectors.partitioningBy(CollectorsExamples::isPrimeNumber));

        // About partitioningBy vs groupingBy
        // Collectors.groupingBy groups stream elements into multiple categories using any object type as a key,
        // whereas Collectors.partitioningBy splits a stream into exactly two categories (true and false)
        // based on a boolean condition

        System.out.println("partitionNumbersListIntoPrimeandNonPrime: " + primeNumbers);
        return this;
    }

    public CollectorsExamples totalCountOfWordsOfEachLength() {
        System.out.println("totalCountOfWordsOfEachLength");
        Map<Integer, Long> maps = Stream.of("apple","banana", "cherry", "date", "fig")
            .peek(word -> System.out.println("Word " + word + " Length: " + word.length()))
            .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println("totalCountOfWordsOfEachLength: " + maps);
        return this;
    }

    public CollectorsExamples highestPaidSalaryByDepartment() {
        System.out.println("highestPaidSalaryWithCollect");
        Map<String,CommonExamples.Employee> map = Stream.of(
            new CommonExamples.Employee("Alice", "Accounting", 50000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Bob", "IT", 60000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Charlie", "HR", 45000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("David", "HR", 55000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("John", "IT", 66000, LocalDate.of(2026,1,1), 30)
        )
            // .collect(Collectors.groupingBy(CommonExamples.Employee::department, Collectors.maxBy((first, next) -> Double.compare(first.salary(), next.salary())))); // Map<String, Optional<Employee>>
            .collect(Collectors.groupingBy(
                CommonExamples.Employee::department,
                Collectors.collectingAndThen( // This required to remove the Optional<Employee>
                    Collectors.maxBy(Comparator.comparingDouble(CommonExamples.Employee::salary)),
                    Optional::get
                )
            ));
        System.out.println("highestPaidSalaryByDepartment: " + map);
        return this;
    }

    public CollectorsExamples getAllEmployeeNameAsSingleCommaSeparatedString() {
        System.out.println("getAllEmployeeNameAsSingleCommaSeparatedString");
        String concatString = Stream.of(
            new CommonExamples.Employee("Alice", "Accounting", 50000, LocalDate.of(2015,1,1), 30),
            new CommonExamples.Employee("Bob", "IT", 60000, LocalDate.of(2010,1,1), 30),
            new CommonExamples.Employee("Charlie", "HR", 45000, LocalDate.of(2018,1,1), 30),
            new CommonExamples.Employee("David", "HR", 55000, LocalDate.of(2005,1,1), 30),
            new CommonExamples.Employee("John", "IT", 66000, LocalDate.of(2007,1,1), 30)
        ).map(CommonExamples.Employee::name)
            .collect(Collectors.joining(","));
        System.out.println("getAllEmployeeNameAsSingleCommaSeparatedString: " + concatString);
        return this;
    }

    public CollectorsExamples employeeWithLongestTenure() {
        System.out.println("employeeWithLongestTenure");
        // Optional<CommonExamples.Employee> employee = Stream.of(
        CommonExamples.Employee employee = Stream.of(
            new CommonExamples.Employee("Alice", "Accounting", 50000, LocalDate.of(2015,1,1), 30),
            new CommonExamples.Employee("Bob", "IT", 60000, LocalDate.of(2010,1,1), 30),
            new CommonExamples.Employee("Charlie", "HR", 45000, LocalDate.of(2018,1,1), 30),
            new CommonExamples.Employee("David", "HR", 55000, LocalDate.of(2005,1,1), 30),
            new CommonExamples.Employee("John", "IT", 66000, LocalDate.of(2000,1,1), 30)
        )
        // .min(Comparator.comparingDouble(CommonExamples.Employee::joiningYear));  // Optional<CommonExamples.Employee>
        .collect(
            Collectors.collectingAndThen(
                Collectors.minBy(Comparator.comparing(CommonExamples.Employee::joiningDate)),
                Optional::get
            )
        );
        System.out.println("employeeWithLongestTenure: " + employee);
        return this;
    }

    public CollectorsExamples salariesSumByDepartment() {
        System.out.println("salariesSumByDepartment");
        Map<String, Double> salaries = Stream.of(
            new CommonExamples.Employee("Alice", "Accounting", 50000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Bob", "IT", 60000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Charlie", "HR", 45000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("David", "HR", 55000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("John", "IT", 66000, LocalDate.of(2026,1,1), 30)
        )
        .collect(
            Collectors.groupingBy(
                CommonExamples.Employee::department,
                Collectors.summingDouble(CommonExamples.Employee::salary)
            )
        );
        System.out.println("salariesSumByDepartment: " + salaries);
        return this;
    }

    @Override
    public void demo() {
        System.out.println("Stream demo");
        new CollectorsExamples()
            .findAverageSalaryWithCollect()
            .sumAllOddNumbers()
            .findMostFrequentlyOccurringCharacterInString()
            .groupEmployeeListByDepartment()
            .partitionNumbersListIntoPrimeandNonPrime()
            .totalCountOfWordsOfEachLength()
            .highestPaidSalaryByDepartment()
            .getAllEmployeeNameAsSingleCommaSeparatedString()
            .employeeWithLongestTenure()
            .salariesSumByDepartment()
        ;
    }
}
