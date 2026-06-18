package com.lavalliere.daniel.projects.streams;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

import java.security.InvalidParameterException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Map.entry;

@IsDemoable
public class MultiOpExamples implements Demoable {

    public record Student(String name, List<Integer> marks) {
        double average() {
            return marks.stream().collect(Collectors.averagingInt(Integer::intValue));
        }
    }

    public record Transaction (LocalDate date, double amount, String userId, String id) {}

    public record Product(String name, String category, double price, double sales) {}

    public MultiOpExamples findSecondMostFrequentCharInString() {
        System.out.println("findSecondMostFrequentCharInString");
        Character match = "aabbccdddeeffffgg".chars()
            .mapToObj(c -> (char)c)  // Convert to stream<Character> from int value
            .collect(Collectors.groupingBy(
                Function.identity(), // Use the value being processed as a key to the map
                Collectors.counting())  // Count number of occurrences
            )  // Map of character to its occurrences count
            .entrySet().stream()
            .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
            .skip(1) // Skip the first
            .findFirst()
            .map(Map.Entry::getKey)
            .orElse('\0');
        System.out.println("findSecondMostFrequentCharInString: " + match);
        return this;
    }

    public MultiOpExamples findLongestWordInList() {
        System.out.println("findLongestWordInList");
        String longest = Stream.of("Hello World", "Java Streams are powerful")
            .flatMap(sentence -> Stream.of(sentence.split(" ")))  // Convert each sentence in streams of words
            .max(Comparator.comparingInt(String::length)) // Longest word found
            .orElse(null);
        System.out.println("longest = " + longest);
        return this;
    }

    public MultiOpExamples findDepartmentWithHighestAverageSalary() {
        System.out.println("findDepartmentWithHighestAverageSalary");
        Stream.of(
            new CommonExamples.Employee("Alice", "Accounting", 50000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Bob", "IT", 60000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Charlie", "HR", 45000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("David", "HR", 55000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("John", "IT", 66000, LocalDate.of(2026,1,1), 30)
        ).collect(Collectors.groupingBy(CommonExamples.Employee::department,  Collectors.averagingDouble(CommonExamples.Employee::salary))) // Map<String, Double>
         .entrySet().stream()  // Convert to Stream<Map.Entry>
         .max(Map.Entry.comparingByValue()) // max salary -> Optional<Map.Entry<String, Double>>
         .stream().forEach(entry -> System.out.println(entry.getKey() + " department has highest average salaray of " + entry.getValue()));
//       .map(Map.Entry::getKey)
//       .findFirst().orElse("None found");
        return this;
    }

    public MultiOpExamples convertSentencesListToSortedListOfUniqueWordsCaseInsentitive() {
        System.out.println("convertSentencesListToSortedListOfUniqueWordsCaseInsentitive");
        List<String> words = Stream.of("Hello world", "hello there", "world of Java")
            .flatMap(sentence -> Stream.of(sentence.split(" ")))
            .sorted(Comparator.comparing(String::toLowerCase))
            .map(String::toLowerCase)
            .distinct()
            .toList();
        System.out.println("convertSentencesListToSortedListOfUniqueWordsCaseInsentitive: " + words);
        return this;
    }

    public MultiOpExamples findTheKLargestElementInList() {
        System.out.println("findTheKLargestElementInList");
        int k = 2;
        int result = Stream.of(3, 5, 2, 8, 1)
            .sorted(Comparator.comparingInt(Integer::intValue).reversed())
            .skip(k - 1)
            .findFirst()
            .orElseThrow(InvalidParameterException::new);
        System.out.println("The " + k + " largest value is " + result);
        return this;
    }

    public MultiOpExamples checkIf2ListsArePermutationOfEachOther() {
        System.out.println("checkIf2ListsArePermutationOfEachOther");
        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = List.of(3, 2, 1);

        boolean isPermutattion = list1.size() == list2.size() &&    // Differ if size differs
            list1.stream().sorted().toList().equals(                // Compare sorted lists using list equals comparison
            list2.stream().sorted().toList()                        // Standard Collection equals are based on content not ref
        );
        System.out.println("isPermutattion: " + isPermutattion);
        return this;
    }

    public MultiOpExamples mergeListsIntoSingleSortedListWithoutDuplicates() {
        System.out.println("mergeListsIntoSingleSortedListWithoutDuplicates");
        List<Integer> list1 = List.of(1, 3, 5);
        List<Integer> list2 = List.of(2, 4, 6);
        List<Integer> list3 = List.of(3, 5, 7);
        List<Integer> nums = Stream.of(list1, list2, list3)
            .flatMap(List::stream)
            .distinct()
            .sorted()
            .toList();
        System.out.println("mergeListsIntoSingleSortedListWithoutDuplicates: " + nums);
        return this;
    }

    public MultiOpExamples findTop3MostFrequentlyOccurringWord() {
        System.out.println("findTop3MostFrequentlyOccurringWord");
        List<String> result = Stream.of("apple", "banana", "apple", "cherry", "banana", "apple", "cherry", "cherry", "banana")
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(3)
            .map(Map.Entry::getKey)
            .toList();
        System.out.println("findTop3MostFrequentlyOccurringWord: " + result);
        return this;
    }

    public MultiOpExamples studentWithHighestAverageMarkAcrossMultipleSubjects() {
        System.out.println("studentWithHighestAverageMarkAcrossMultipleSubjects");
        Student student = Stream.of(
            new Student("Alice", List.of(80, 90, 85)),
            new Student("Bob", List.of(70, 75, 80)),
            new Student("Charlie", List.of(90, 95, 100))
        ).collect(Collectors.maxBy(Comparator.comparingDouble(Student::average))).orElse(null);
        System.out.println("studentWithHighestAverageMarkAcrossMultipleSubjects: " + student);
        return this;
    }

    public MultiOpExamples wordCountInParagraphIgnoreCase() {
        System.out.println("wordCountInParagraphIgnoreCase");
        Map<String, Long> counts = Stream.of("Hello world hello".split(" "))
            .map(String::toLowerCase)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("wordCountInParagrapheIgnoreCase: " + counts);
        return this;
    }

    public MultiOpExamples longestPalindromeFromList() {
        System.out.println("longestPalindromeFromList");
        String longest = Stream.of("madam", "racecar", "applevel", "level")
            .filter(word ->
                IntStream.range(0, word.length() / 2)
                .allMatch(i -> word.charAt(i) == word.charAt(word.length() - 1 - i))
            )
            .max(Comparator.comparingInt(String::length))
            .orElse(null);
        System.out.println("longestPalindromeFromList: " + longest);
        return this;
    }

    public MultiOpExamples printFibonacciSeriesUpToNTerms() {
        System.out.println("printFibonacciSeriesUpToNTerms");
        int N = 10;
        Stream.iterate(   // Create a sequential ordered stream
            new int[] {0, 1}, // Start from
            fib -> new int[]{ fib[1], fib[0] + fib[1]}  // 0, 1, 1, 2, 3, 5, 8 ... ->  (0,1),(1,1),(1,2),(2,3) etc...
        ).limit(N)   // Generate N pairs
         .map(fib -> fib[0]) // Extract the first element of each pair
         .forEach(System.out::println);
        return this;
    }

    // 2.11.13 - 138
    public MultiOpExamples mostExpensiveProductByCategoryFromProductList() {
        System.out.println("mostExpensiveProductByCategoryFromProductList");
        Map<String, Product> prodCat = Stream.of(
            new Product("Laptop", "Electronics", 1200, 1200),
            new Product("Smartphone", "Electronics", 800, 800),
            new Product("Tablet", "Electronics", 500, 500),
            new Product("Shirt", "Clothing", 50, 50),
            new Product("Jeans", "Clothing", 80, 80),
            new Product("Shoes", "Clothing", 120, 120)
        ).collect(Collectors.groupingBy(
            Product::category,
            Collectors.collectingAndThen(   // This is required to get rid of Optional<Product>
                Collectors.maxBy(Comparator.comparingDouble(Product::price)),
                Optional::get
            )
        ));
        System.out.println("mostExpensiveProductByCategoryFromProductList: " + prodCat);
        return this;
    }

    public MultiOpExamples findCommonElementsBetween2Lists() {
        System.out.println("findCommonElementsBetween2Lists");
        List<Integer> list1 = List.of(1,2,3,4);
        List<Integer> list2 = List.of(3,4,5,6);
        List<Integer> comonLIst = Stream.of(list1, list2)
            .flatMap(List::stream)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .filter(entry -> entry.getValue() > 1)
            .map(Map.Entry::getKey)
            .toList();
        System.out.println("findCommonElementsBetween2Lists: " + comonLIst);
        return this;
    }

    public MultiOpExamples convertMapWithReverseType() {
        System.out.println("convertMapWithReverseType");
        // Convert from Map["A"] = [1, 2],  Map["B"] = [2, 3]
        // To a map where each value map to the original key where it was  initially associated with
        // Map[1] = ["A"], Map[2] = ["A", "B"], Map[3] = ["B"]
        Map<String, List<Integer>> omap = Map.ofEntries(  // Java 9 +
            entry("A", List.of(1, 2)),
            entry("B", List.of(2, 3))
        );
        System.out.println("convertMapWithReverseType original map: " + omap);
        Map<Integer, List<String>> dmap = omap.entrySet().stream()  // Process each individual map entries
            .flatMap(entry ->
                entry.getValue().stream()  // Iterate over each element of the List<Integer>
                    .map(value -> Map.<Integer, String>entry(value, entry.getKey()))  // Create Stream of new entry for each int value associated to its current key
            ).collect(  // Group by key
                Collectors.groupingBy(
                    Map.Entry::getKey,
                    Collectors.mapping(  // Similar to Collectors.collectingAndThen : 1-) The Supplier 2-) What operation to apply next
                        Map.Entry::getValue, // Extract the current pair value
                        Collectors.toList()  // Collect them into a lis
                    )  // Convert the element before next processing
                )
            );
        System.out.println("convertMapWithReverseType final map: " + dmap);
        return this;
    }

    public MultiOpExamples findKthSmallestElementInUnSortedList() {
        System.out.println("findKthSmallestElementInUnSortedList");
        int k = 3;
        int num = Stream.of(7, 10, 4, 3, 20, 15, 4)
            .distinct()
            .sorted()
            .skip(k - 1)
            .findFirst().orElseThrow(IllegalArgumentException::new);
        System.out.println("findKthSmallestElementInUnSortedList for k= " + k + " is: " + num);
        return this;
    }

    public MultiOpExamples generatePrimeNumbersUptoN() {
        System.out.println("generatePrimeNumbersUptoN");
        int n = 20;
        IntStream.rangeClosed(2, 20)
            .filter(CollectorsExamples::isPrimeNumber)
            .forEach(System.out::println);
        return this;
    }

    public MultiOpExamples findTheFirstNonRepeatingCharacterInAString() {
        System.out.println("findTheFirstNonRepeatingCharacterInAString");
        Character dc = "swiss".chars()
            .mapToObj(c-> (char)c)  // Stream<Character>
            .collect(Collectors.groupingBy(
                Function.identity(),    // Function : The key for the map entry
                LinkedHashMap::new,     // Supplier : Map implementation to use (ie: keep insertion ordering)
                Collectors.counting()   // Collector: What to do with the data
            )).entrySet().stream()
            .filter(entry -> entry.getValue() == 1)  // Only non-reoccurring char
            .map(Map.Entry::getKey)
            .findFirst().orElse('\0');
        System.out.println("findTheFirstNonRepeatingCharacterInAString: " + dc);
        return this;
    }

    // Not really a multi stream example as only using it to sum ...
    // The following only works to find a single missing element where all elements but one in the sequence are present
    public MultiOpExamples findMissingNumberIn1ToNSequence() {
        System.out.println("findMissingNumberIn1ToNSequence");
        int[] arr = {1, 2, 4, 5};
        int n = arr.length + 1;
        int expectedSum = IntStream.rangeClosed(1, n).sum();
        int actualSum = Arrays.stream(arr).sum();
        int missingNumber = expectedSum - actualSum;
        System.out.println("findMissingNumberIn1ToNSequence: " + missingNumber);
        return this;
    }

    public MultiOpExamples reverseALinkedList() {
        System.out.println("reverseALinkedList");
        LinkedList<Integer> llist = new LinkedList<>(List.of(1,2,3,4,5));

        // Stream does NOT support traversing in reverse order
        // So need to use methods from Collection implementation
        LinkedList<Integer> dllist = llist.stream()
            .collect(Collectors.collectingAndThen(
                Collectors.toCollection(LinkedList::new), // Create an alternate collection to Set/List
                list  -> {  // LinkedList<Integer>
                    LinkedList<Integer> newList = new LinkedList<>();
                    list.descendingIterator().forEachRemaining(newList::add); // Traverse the list in reverse order and for each entry run the command
                    return newList;
                }
            )
        );
        System.out.println("reverseALinkedList: " + dllist);
        return this;
    }

    public MultiOpExamples findBusiestHourInTimestampList() {
        System.out.println("findBusiestHourInTimestampList");
        String hour = Stream.of("12:00", "12:30", "12:45", "13:00", "12:30")
            .map(timestamp -> timestamp.split(":")[0])
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))  // Sort by from highest to lowest count
            .map(Map.Entry::getKey)
            .findFirst().orElseThrow(IllegalArgumentException::new);
        System.out.println("findBusiestHourInTimestampList: "+ hour );
        return this;
    }

    public MultiOpExamples findEmployeesWhoJoinedWithinLastYear() {
        System.out.println("findEmployeesWhoJoinedWithinLastYear");
        LocalDate currentDate = LocalDate.of(2024,1,1);
        List<CommonExamples.Employee> employees = Stream.of(
            new CommonExamples.Employee("Alice", "Accounting", 50000, LocalDate.of(2022,10,15), 30),
            new CommonExamples.Employee("Bob", "IT", 60000, LocalDate.of(2023,1,20), 30),
            new CommonExamples.Employee("Charlie", "HR", 45000, LocalDate.of(2021,5,10), 30),
            new CommonExamples.Employee("David", "HR", 55000, LocalDate.of(2023,3,5), 30)
        ).filter(employee ->
            // The main difference between Duration.between() and Period.between() in Java is that
            // Duration measures time-based amounts (seconds and nanoseconds),
            // while Period measures date-based amounts (years, months, and days)
            Period.between(
                employee.joiningDate(),
                currentDate
            ).getYears() < 1)
         .collect(Collectors.toList());
        System.out.println("findEmployeesWhoJoinedWithinLastYear: " + employees);
        return this;
    }

    public MultiOpExamples findStudentsWhoHaveScoredMoreThan75PercentInAllSubject() {
        System.out.println("findStudentsWhoHaveScoredMoreThan75PercentInAllSubject");
        List<Student> students = Stream.of(
            new Student("Alice", List.of(80, 90, 85)),
            new Student("Bob", List.of(70, 75, 80)),
            new Student("Charlie", List.of(90, 95, 100))
        ).filter(student -> student.marks.stream().allMatch(mark -> mark > 75))
         .collect(Collectors.toList());
        System.out.println("findStudentsWhoHaveScoredMoreThan75PercentInAllSubject: " + students);
        return this;
    }

    public MultiOpExamples sortTransactionListByDateThenByAmountDescending() {
        System.out.println("sortTransactionListByDateThenByAmountDescending");
        List<Transaction> transactions = Stream.of(
            new Transaction(LocalDate.of(2023, 10, 1), 100.0, "U1", "T1"),
            new Transaction(LocalDate.of(2023, 10, 1), 200.0, "U1", "T1"),
            new Transaction(LocalDate.of(2023, 9, 15), 150.0, "U1", "T1"),
            new Transaction(LocalDate.of(2023, 9, 15), 50.0, "U1", "T1")
        ).sorted(
            Comparator.comparing(Transaction::date)
            .thenComparing(Comparator.comparing(Transaction::amount).reversed())
        ).toList();
        System.out.println("sortTransactionListByDateThenByAmountDescending: " + transactions);
        return this;
    }

    public MultiOpExamples findFirst5WordsAppearingOnlyOnceFromParagraphIgnoreCase() {
        System.out.println("findFirst5WordsAppearingOnlyOnceFromParagraphIgnoreCase");
        List<String> uniqueWords = Arrays.stream("Hello world hello java world !".split(" "))
            .map(String::toLowerCase)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .filter(entry -> entry.getValue() == 1)
            .map(Map.Entry::getKey)
            .toList();
        System.out.println("findFirst5WordsAppearingOnlyOnceFromParagraphIgnoreCase: " + uniqueWords);
        return this;
    }

    public MultiOpExamples findFirst5DistinctWordsFromParagraphIgnoreCase() {
        System.out.println("findFirst5DistinctWordsFromParagraphIgnoreCase");
        List<String> uniqueWords = Arrays.stream("Hello world hello java world !".split(" "))
            .map(String::toLowerCase)
            .distinct()
            .limit(5)
            .toList();
        System.out.println("findFirst5DistinctWordsFromParagraphIgnoreCase: " + uniqueWords);
        return this;
    }

    public MultiOpExamples findEmployeesAverageAgePerDepartmentThenFindDepartmentWithHighest() {
        System.out.println("findEmployeesAverageAgePerDepartmentThenFindDepartmentWithHighest");
        Map.Entry entry = Stream.of(
            new CommonExamples.Employee("Alice", "HR", 50000, LocalDate.of(2022,10,30), 30),
            new CommonExamples.Employee("Bob", "IT", 60000, LocalDate.of(2023,1,20), 25),
            new CommonExamples.Employee("Charlie", "HR", 45000, LocalDate.of(2021,5,10), 35),
            new CommonExamples.Employee("David", "IT", 55000, LocalDate.of(2023,3,5), 40),
            new CommonExamples.Employee("Eve", "Finance", 55000, LocalDate.of(2023,3,5), 50)
        ).collect(Collectors.groupingBy(CommonExamples.Employee::department, Collectors.averagingDouble(CommonExamples.Employee::age)))
            .entrySet().stream()
            .max(Comparator.comparing(Map.Entry::getValue))
            .orElseThrow(IllegalStateException::new);
            // .map(Map.Entry::getKey).orElse("Not found");

        System.out.println("findEmployeesAverageAgePerDepartmentThenFindDepartmentWithHighest: " + entry.getKey() + " average: " +  entry.getValue() );
        return this;
    }

    public MultiOpExamples findTop3HighestSellingProductByCategory() {
        System.out.println("findTop3HighestSellingProductByCategory");
        // Map<String, Optional<Product>> products =
        Map<String, List<Product>> products = Stream.of(
            new Product("Laptop", "Electronics", 1000, 1000),
            new Product("Smartphone", "Electronics", 500, 1500),
            new Product("Tablet", "Electronics", 400, 800),
            new Product("TV", "Electronics", 2000, 8000),
            new Product("Shirt", "Clothing", 50, 100),
            new Product("Jeans", "Clothing", 100, 400),
            new Product("Shoes", "Clothing", 100, 300),
            new Product("Hat", "Clothing", 50, 50)
        ).collect(Collectors.groupingBy(Product::category))  // Store as Map<Category, List<Product>>
         .entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,  // key is Category
                entry -> entry.getValue().stream()       // Stream all products listed
                    .sorted(Comparator.comparing(Product::sales).reversed()) // Iterating through values so Product not Optional<Product>
                    .limit(3)
                .collect(Collectors.toList())
            ));
        System.out.println("findTop3HighestSellingProductByCategory: " + products);
        return this;
    }

    public MultiOpExamples findEmployeesWithVowelsInTheirNamesThenSortThem() {
        System.out.println("findEmployeesWithVowelsInTheirNamesThenSortThem");
        List<String> names = Stream.of(
            new CommonExamples.Employee("Alice", "Accounting", 50000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Bryn", "IT", 60000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Charlie", "HR", 45000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Rhys", "HR", 55000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Gwyn", "IT", 66000, LocalDate.of(2026,1,1), 30),
            new CommonExamples.Employee("Bob", "Finance", 75000, LocalDate.of(2026,1,1), 30)
        ).filter(employee -> employee.name().matches(".*[AEIOUaeiou].*"))
         .sorted(Comparator.comparing(CommonExamples.Employee::name))
         .map(CommonExamples.Employee::name)
         .toList();
        System.out.println("findEmployeesWithVowelsInTheirNamesThenSortThem: " + names);
        return this;
    }

    public MultiOpExamples groupEmployeesThoseWithVowelsAndThoseWithout() {
        System.out.println("groupEmployeesThoseWithVowelsAndThoseWithout");
        Map<Boolean, List<String>> employees = Stream.of(
                new CommonExamples.Employee("Alice", "Accounting", 50000, LocalDate.of(2026,1,1), 30),
                new CommonExamples.Employee("Bryn", "IT", 60000, LocalDate.of(2026,1,1), 30),
                new CommonExamples.Employee("Charlie", "HR", 45000, LocalDate.of(2026,1,1), 30),
                new CommonExamples.Employee("Rhys", "HR", 55000, LocalDate.of(2026,1,1), 30),
                new CommonExamples.Employee("Gwyn", "IT", 66000, LocalDate.of(2026,1,1), 30),
                new CommonExamples.Employee("Bob", "Finance", 75000, LocalDate.of(2026,1,1), 30)
            ).map(CommonExamples.Employee::name)
            .collect(Collectors.partitioningBy( name -> name.matches(".*[AEIOUaeiou].*")));
        System.out.println("groupEmployeesThoseWithVowelsAndThoseWithout: " + employees);
        return this;
    }

    public MultiOpExamples groupTransactionsSortedByMonthAndCountThem() {
        System.out.println("groupTransactionsSortedByMonthAndCountThem");
        Map<Month, Long> trCount = Stream.of(
            new Transaction(LocalDate.of(2023, 1, 15), 100.0, "U1", "T1"),
            new Transaction(LocalDate.of(2023, 1, 20), 200.0, "U1", "T1"),
            new Transaction(LocalDate.of(2023, 2, 10), 150.0, "U1", "T1"),
            new Transaction(LocalDate.of(2023, 2, 25), 300.0, "U1", "T1"),
            new Transaction(LocalDate.of(2023, 3, 5), 250.0, "U1", "T1")
        ).collect(
            Collectors.groupingBy(   // Use TreeMap to get a sorted map
                transaction -> transaction.date.getMonth(),
                TreeMap::new,
                Collectors.counting()
            ));

            // Unsorted
            // Collectors.groupingBy(
            //      transaction -> transaction.date.getMonth(),
            //     Collectors.counting()
            // ));

        System.out.println("groupTransactionsSortedByMonthAndCountThem: " + trCount);
        return this;
    }

    public MultiOpExamples convertDatesToCorrespondingDayOfTheWeekAndCountOccurences() {
        System.out.println("convertDatesToCorrespondingDayOfTheWeekAndCountOccurences");
        Map<DayOfWeek, Long> result = Stream.of(
            LocalDate.of(2023,1,15),
            LocalDate.of(2023,1,16),
            LocalDate.of(2023,1,17),
            LocalDate.of(2023,1,18),
            LocalDate.of(2023,1,19),
            LocalDate.of(2023,1,20),
            LocalDate.of(2023,1,21),
            LocalDate.of(2023,1,22),
            LocalDate.of(2023,1,23),
            LocalDate.of(2023,1,24),
            LocalDate.of(2023,1,25)
        ).map(localDate -> localDate.getDayOfWeek())
         .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("convertDatesToCorrespondingDayOfTheWeekAndCountOccurences: " + result);
        return this;
    }

    public MultiOpExamples findEmployeeWithSecondHighestSalaryByDepartment() {
        System.out.println("findEmployeeWithSecondHighestSalaryByDepartment");
        Map<String, CommonExamples.Employee> result = Stream.of(
            new CommonExamples.Employee("Alice", "HR", 50000, LocalDate.of(2022,10,30), 30),
            new CommonExamples.Employee("Bob", "IT", 60000, LocalDate.of(2023,1,20), 25),
            new CommonExamples.Employee("Charlie", "HR", 55000, LocalDate.of(2021,5,10), 35),
            new CommonExamples.Employee("David", "IT", 70000, LocalDate.of(2023,3,5), 40),
            new CommonExamples.Employee("Eve", "Finance", 65000, LocalDate.of(2023,3,5), 50),
            new CommonExamples.Employee("Frank", "Finance", 75000, LocalDate.of(2023,3,5), 50)
        )
        .collect(Collectors.groupingBy(
            CommonExamples.Employee::department,
            Collectors.collectingAndThen(
                Collectors.toList(), // The content to process
                employees -> employees.stream()
                    // .peek(employee -> System.out.println("An employee: " + employee))
                    .sorted(Comparator.comparing(CommonExamples.Employee::salary).reversed())
                    .skip(1) // ie: Each must have at least 2 instances else will fail ie: null as employee
                    .findFirst()
                    .orElse(null)
            ))
        );
        System.out.println("findEmployeeWithSecondHighestSalaryByDepartment: " + result);
        return this;
    }

    public MultiOpExamples findMostCommonDomainNameInEmailList() {
        System.out.println("findMostCommonDomainNameInEmailList");
        String domain = Stream.of("user1@example.com","user2@domain.com","user3@example.com", "user4@example.com","user5@domain.com")
            .map(email -> email.substring(email.indexOf("@") + 1))
            .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
            )).entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
        System.out.println("findMostCommonDomainNameInEmailList: " + domain);
        return this;
    }

    public MultiOpExamples findNthSmallestNumberInList() {
        System.out.println("findNthSmallestNumberInList");
        int n = 3 ;
        int num = Stream.of(5, 3, 8, 1, 4).sorted().skip(n - 1).findFirst().orElseThrow(IllegalArgumentException::new);
        System.out.println("findNthSmallestNumberInList: with n=" + n + " is: " + num);
        return this;
    }

    public MultiOpExamples duplicateWordsInParagraph(){
        System.out.println("duplicateWordsInParagraph");
        List<String> words = Arrays.stream("Hello hello world world world".split(" "))
                .map(word -> word.toLowerCase())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println("duplicateWordsInParagraph: " + words);
        return this;
    }

    public MultiOpExamples findNumbersDivisibleBy2And3And5() {
        System.out.println("findNumbersDivisibleBy2And3And5");
        List<Integer> nums = Stream.of(10,15,30,45,60,75)
                .filter(num -> num % 2 == 0 && num %3 == 0 && num %5 == 0)
                .toList();
        System.out.println("findNumbersDivisibleBy2And3And5: " + nums);
        return this;
    }

    public MultiOpExamples findLongestIncreasingSubsequenceInNumberList() {
        System.out.println("findLongestIncreasingSubsequenceInNumberList");
        List<Integer> nums = List.of(10,22,9,33,21,50,41,60);

        // NOTE: Resolution of this has NOTHING to do with streams, only used to determine the max
        int[] depths = new int[nums.size()];
        Arrays.fill(depths, 1);

        for(int endIndex=1; endIndex < nums.size(); endIndex++) {
            for(int startIndex=0; startIndex < endIndex; startIndex++) {
                if (nums.get(endIndex) > nums.get(startIndex) &&
                    depths[endIndex] < depths[startIndex] + 1) {
                    depths[endIndex] = depths[startIndex] + 1;
                }
            }
        }

        int maxLength = Arrays.stream(depths).max().orElse(0);
        System.out.println("findLongestIncreasingSubsequenceInNumberList: " + maxLength);
        return this;
    }

    public MultiOpExamples reverseQueue() {
        System.out.println("reverseQueue");
        Queue<Integer> queue = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        Queue<Integer> reversedQueue = new LinkedList<>();

        queue.stream()
            .collect(Collectors.toCollection(LinkedList::new))
            .descendingIterator().forEachRemaining(reversedQueue::add); // Using LinkedList::forEachRemaining
                                                                        // Also possible: ArrayDeque, TreeSet
                                                                        // java.util.concurrent.ConcurrentSkipListSet (threadsafe)
        System.out.println("reverseQueue: " + reversedQueue);
        return this;
    }

    public MultiOpExamples extractAllHashtagsFromListAndCountOccurrences() {
        System.out.println("extractAllHashtagsFromListAndCountOccurrences");
        Map<String, Long> hashtags = Stream.of("#Java is great", "I love #coding", "#Java is awsome")
            .flatMap(word -> Arrays.stream(word.split(" ")))
            .filter(word -> word.startsWith("#"))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("extractAllHashtagsFromListAndCountOccurrences: " + hashtags);
        return this;
    }

    public MultiOpExamples findUniqueWordsInParagraphIgnoreCaseAndTheirLength() {
        System.out.println("findUniqueWordsInParagraphIgnoreCaseAndTheirLength");
        Map<String, Integer> result = Arrays.stream("Hello world! Hello everyone, This is a test.".split("\\W+")) // Use Word regex so that [! , .] will not be taken into account as part of the words
            .map(String::toLowerCase)
            .distinct()
            .collect(Collectors.toMap(
                Function.identity(),
                word -> word.length()
            ));
        System.out.println("findUniqueWordsInParagraphIgnoreCaseAndTheirLength: " + result);
        return this;
    }

    public MultiOpExamples numbersThatAreBothPerfectSquareAndPerfectCube() {
        System.out.println("numbersThatAreBothPerfectSquareAndPerfectCube");

        /*
            In Java, the expression Math.sqrt(n) % 1 == 0 is used to check if a number n is a perfect square
            (an integer that has a whole number as its square root, such as 4, 9, or 16).

            How it Works Breakdown
            - Math.sqrt(n): Calculates the square root of \(n\) and returns it as a double (floating-point number).
              For example, if n = 9, it returns 3.0. If n = 8, it returns 2.8284....
            - % 1: The modulo operator (%) finds the remainder of dividing the square root by 1.
              For any integer-valued double (like 3.0), dividing by 1 leaves a remainder of 0.0.
              For any fractional number (like 2.8284...), it leaves the decimal remainder (0.8284...).
            - == 0: Checks if that remainder is exactly zero.
              If it is true, it means the square root has no fractional part and is a whole number.
         */

        List<Integer> nums = Stream.of(1, 64, 100, 125, 729)
            .filter(n -> Math.sqrt(n) % 1 == 0 && Math.cbrt(n) % 1 == 0)
            .toList();
        System.out.println("numbersThatAreBothPerfectCube: " + nums);
        return this;
    }

    public MultiOpExamples mergeMultipleListsIntoASingleSortedListWithoutDuplicatesAndValuesGreaterThanN() {
        System.out.println("mergeMultipleListsIntoASingleSortedListWithoutDuplicatesAndValuesGreaterThanN");
        int threshold = 5;
        List<Integer> nums = Stream.of(
            List.of(1, 4, 7),
            List.of(2, 5, 8),
            List.of(3, 6, 9)
        ).flatMap(List::stream)
            .distinct()
            .filter(n -> n > threshold)
            .sorted()
            .toList();
        System.out.println("mergeMultipleListsIntoASingleSortedListWithoutDuplicatesAndValuesGreaterThanN: " + nums);
        return this;
    }

    public MultiOpExamples findMostCommonStartingLetterInWordList() {
        System.out.println("findMostCommonStartingLetterInWordList");
        Character c = Stream.of("apple", "banana", "apricot", "blueberry", "avocado")
            .collect(
                Collectors.groupingBy(
                    word -> word.charAt(0),
                    Collectors.counting())
            ).entrySet().stream().max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey).orElse('\0');
        System.out.println("findMostCommonStartingLetterInWordList: " + c);
        return this;
    }

    public MultiOpExamples findEmployeeWithLongestConsecutiveServicePeriod() {
        System.out.println("findEmployeeWithLongestConsecutiveServicePeriod");
        CommonExamples.Employee employee = Stream.of(
            new CommonExamples.Employee("Alice", "Accounting", 50000, LocalDate.of(2015,10,15), 30),
            new CommonExamples.Employee("Bob", "IT", 60000, LocalDate.of(2010,1,20), 30),
            new CommonExamples.Employee("Charlie", "HR", 45000, LocalDate.of(2018,5,10), 30),
            new CommonExamples.Employee("David", "HR", 55000, LocalDate.of(2005,3,5), 30)
        ).min(Comparator.comparing(CommonExamples.Employee::joiningDate))
         .orElse(null);
        System.out.println("findEmployeeWithLongestConsecutiveServicePeriod: " + employee);
        return this;
    }

    public MultiOpExamples findMedianSalaryInEmployeeList() {
        System.out.println("findMedianSalaryInEmployeeList");
        List<CommonExamples.Employee> employees = List.of(new CommonExamples.Employee("Alice", "Accounting", 50000, LocalDate.of(2015,10,15), 30),
            new CommonExamples.Employee("Bob", "IT", 60000, LocalDate.of(2010,1,20), 30),
            new CommonExamples.Employee("Charlie", "HR", 70000, LocalDate.of(2018,5,10), 30),
            new CommonExamples.Employee("David", "HR", 80000, LocalDate.of(2005,3,5), 30),
            new CommonExamples.Employee("Eve", "HR", 90000, LocalDate.of(2005,3,5), 30));
        Double medianSalaray = employees.stream()
            .mapToDouble(CommonExamples.Employee::salary)
            .sorted()
            .skip((employees.size() -1)  / 2 ) // Skip the middle element(s)
            .limit(2 - employees.size() % 2) // If odd number, take first only
            .average() // If even number of values returned, average them
            .orElse(0);
        System.out.println("findMedianSalaryInEmployeeList: " + medianSalaray);
        return this;
    }

    public MultiOpExamples findSpamMessageBasedOnKeyWordFrequency() {
        System.out.println("findSpamMessageBasedOnKeyWordFrequency");
        List<String> messages = List.of(
            "Get a free iPhone now!",
            "Hello, how are you?",
            "Win a million dollars!",
            "Meeting at 10 AM",
            "Claim your prize today!"
        );
        Set<String> spamKeyWords = Set.of("free", "win", "prize", "million", "dollars");

        List<String> spam = messages.stream()
            .filter(message -> Arrays.stream(message.split(" ")).anyMatch(spamKeyWords::contains))
            .toList();
        System.out.println("findSpamMessageBasedOnKeyWordFrequency: " + spam);
        return this;
    }

    public MultiOpExamples findKthMostExpansiveItemFromInventoryList() {
        System.out.println("findKthMostExpansiveItemFromInventoryList");
        int k = 2;
        Product product = Stream.of(
            new Product("Laptop", "Electronics", 1200, 1200),
            new Product("Smartphone", "Electronics", 800, 800),
            new Product("Tablet", "Electronics", 500, 500),
            new Product("Monitor", "Electronics", 300, 300),
            new Product("Keyboard", "Electronics", 100, 300)
        ).sorted(Comparator.comparing(Product::price).reversed())
            .skip(k - 1)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
        System.out.println("findKthMostExpansiveItemFromInventoryList: " + product);
        return this;
    }

    // An outlier in numerical data is a data point that differs significantly—either being much larger or much smaller—from the rest of the observations in a dataset
    public MultiOpExamples findOutliersInNumericalData() {
        System.out.println("findOutliersInNumericalData");

        List<Integer> sortedData = Stream.of(14, 10, 12, 15, 11,18, 13, 7, 99, 100).sorted().toList();

        // Specific to this problem
        int dataSize = sortedData.size();

        // Calculate the 25th percentile and 75th percentile
        double p25    = sortedData.get((int)(dataSize * 0.25));
        double p75    = sortedData.get((int)(dataSize * 0.75));

        // Calculate the IQR (Interquartile Range)
        double pRange = p75 - p25;

        // Calculate lower and upper bounds for outliers
        double lowerBound = p25 - 1.5 * pRange;
        double upperBound = p75 + 1.5 * pRange;

        System.out.println("findOutliersInNumericalData: lowerBound: " + lowerBound + " upperBound: " + upperBound  );

        List<Integer> outliers = sortedData.stream()
            .peek(n -> System.out.println("Numerical Data: " + n))
            .filter(n -> n < lowerBound || n > upperBound)
            .toList();

        System.out.println("findOutliersInNumericalData: " + outliers);
        return this;
    }

    public MultiOpExamples findOverlappingTimeSlotInSchedulesList() {
        System.out.println("findOverlappingTimeSlotInSchedulesList");

        List<int[]> schedules = Arrays.asList(
            new int[]{4, 9},
            new int[]{1, 3},
            new int[]{2, 5}
        );

        // Need to sort the schedules for comparisons in the algorithm
        List<int[]> sortedSchedules = schedules.stream()
            .sorted(Comparator.<int[]>comparingInt(pair -> pair[0])
            .thenComparing(pair -> pair[1])).toList();

        for(int[] schedule: sortedSchedules) {
            System.out.println("findOverlappingTimeSlotInSchedulesList: Sorted schedules are: " + Arrays.toString(schedule));
        }

        List<int[]> overlappingSchedules = IntStream.range(0, schedules.size() - 1)
            .filter(i -> sortedSchedules.get(i)[1] > sortedSchedules.get(i + 1)[0])  // Last element of first pair > first element of next pair
            .mapToObj(i -> new int[]{sortedSchedules.get(i)[0], sortedSchedules.get(i+1)[1]})
            .toList();

        for(int[] schedule: overlappingSchedules) {
            System.out.println("findOverlappingTimeSlotInSchedulesList: overlapping schedules are: " + Arrays.toString(schedule));
        }

        return this;
    }

    public MultiOpExamples findUniqueBigramsInString() {
        System.out.println("findUniqueBigramsInString");
        // NOTE Bigrams are 2 words sentences

        List<String> words = Arrays.asList("The quick brown fox jumps over the lazy dog".split(" "));
        Set<String> bigrams = IntStream.range(0, words.size() - 1)
            .mapToObj(i -> words.get(i) + " " +  words.get(i + 1))
            .collect(Collectors.toSet());

        System.out.println("findUniqueBigramsInString: " + bigrams);
        return this;
    }

    public MultiOpExamples findTop3WordsInParagraph() {
        System.out.println("findTop3WordsInParagraph");
        List.of(
            "Java is a programming language",
            "Java is used for building applications",
            "Streams in Java are powerful"
        ).stream()
            .flatMap(phrase -> Arrays.stream(phrase.split("\\s+")))
            .map(String::toLowerCase)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()

            // IMPORTANT:
            // FOR Map.Entry ALWAYS use Map.Entry.comparing NOT Collectors.Comparing to have access to reverse() method
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            // .peek(entry -> System.out.println("An entry: " + entry.getKey() + " : " + entry.getValue()))
            .limit(3)
                .forEach(entry -> System.out.println("Top words in paragraph: key: " + entry.getKey() + " value: " + entry.getValue() ));

            // The following shows how to generate a sorted TreeMap using the provided Map.Entry values
            //.collect(Collectors.toMap(
            //    Map.Entry::getKey,
            //    Map.Entry::getValue,
            //    (oldValue, newValue) -> newValue, // Handle duplicate entries
            //    TreeMap::new
            //));
        return this;
    }

    public MultiOpExamples findCustomersWithPurchasesInConsecutiveMonths() {
        System.out.println("findCustomersWithPurchasesInConsecutiveMonths");
        // List<Map.Entry<String, LocalDate>> entries =
        Map<String, List<LocalDate>> purchases = Stream.of(
            Map.entry("C1", LocalDate.of(2026, 1, 1)),
            Map.entry("C2", LocalDate.of(2026, 2, 1)),
            Map.entry("C1", LocalDate.of(2026, 2, 1)),
            Map.entry("C3", LocalDate.of(2026, 3, 1)),
            Map.entry("C2", LocalDate.of(2026, 3, 1)),
            Map.entry("C3", LocalDate.of(2025, 2, 1)),
            Map.entry("C2", LocalDate.of(2026, 5, 1))
        )
        .sorted(Map.Entry.comparingByValue())  // Sort by date for each customer
        .collect(Collectors.groupingBy( // Generate Map<String, List<LocalDate>>
            Map.Entry::getKey,
            Collectors.mapping(Map.Entry::getValue, Collectors.toList())
        )).entrySet().stream()

        // Filter out any Customer without strictly consecutive months purchases
        .filter(entry -> {  // For each Customer, process the list of purchases
            List<LocalDate> dates = entry.getValue();
            if (dates.size() < 2) return false; // Skip any Customer with less than 2 purchases

            return IntStream.range(0, dates.size() - 1)
                .allMatch(
                    // NOTE: Period.between measures date-based time (years, months, days),
                    //       while Duration.between measures time-based time (seconds, nanoseconds)
                    i -> Period.between(dates.get(i), dates.get(i + 1)).toTotalMonths() == 1
                );
        })

        // Generate map to store the results
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("findCustomersWithPurchasesInConsecutiveMonths: " +  purchases);
        return this;
    }

    public MultiOpExamples findFraudulentTransactionsBasedOnFrequencyAndAmount() {
        System.out.println("findFraudulentTransactionsBasedOnFrequencyAndAmount");

        return this;
    }

    @Override
    public void demo() {
        new MultiOpExamples()
            .findSecondMostFrequentCharInString()
            .findLongestWordInList()
            .findDepartmentWithHighestAverageSalary()
            .convertSentencesListToSortedListOfUniqueWordsCaseInsentitive()
            .findTheKLargestElementInList()
            .checkIf2ListsArePermutationOfEachOther()
            .mergeListsIntoSingleSortedListWithoutDuplicates()
            .findTop3MostFrequentlyOccurringWord()
            .studentWithHighestAverageMarkAcrossMultipleSubjects()
            .wordCountInParagraphIgnoreCase()
            .longestPalindromeFromList()
            .printFibonacciSeriesUpToNTerms()
            .mostExpensiveProductByCategoryFromProductList()
            .findCommonElementsBetween2Lists()
            .convertMapWithReverseType()
            .findKthSmallestElementInUnSortedList()
            .generatePrimeNumbersUptoN()
            .findTheFirstNonRepeatingCharacterInAString()
            .findMissingNumberIn1ToNSequence()
            .reverseALinkedList()
            .reverseALinkedList()
            .findBusiestHourInTimestampList()
            .findEmployeesWhoJoinedWithinLastYear()
            .findStudentsWhoHaveScoredMoreThan75PercentInAllSubject()
            .sortTransactionListByDateThenByAmountDescending()
            .findFirst5WordsAppearingOnlyOnceFromParagraphIgnoreCase()
            .findFirst5DistinctWordsFromParagraphIgnoreCase()
            .findEmployeesAverageAgePerDepartmentThenFindDepartmentWithHighest()
            .findTop3HighestSellingProductByCategory()
            .findTop3HighestSellingProductByCategory()
            .findEmployeesWithVowelsInTheirNamesThenSortThem()
            .groupEmployeesThoseWithVowelsAndThoseWithout()
            .groupTransactionsSortedByMonthAndCountThem()
            .convertDatesToCorrespondingDayOfTheWeekAndCountOccurences()
            .findEmployeeWithSecondHighestSalaryByDepartment()
            .findMostCommonDomainNameInEmailList()
            .findNthSmallestNumberInList()
            .duplicateWordsInParagraph()
            .findNumbersDivisibleBy2And3And5()
            .findLongestIncreasingSubsequenceInNumberList()
            .reverseQueue()
            .extractAllHashtagsFromListAndCountOccurrences()
            .findUniqueWordsInParagraphIgnoreCaseAndTheirLength()
            .numbersThatAreBothPerfectSquareAndPerfectCube()
            .mergeMultipleListsIntoASingleSortedListWithoutDuplicatesAndValuesGreaterThanN()
            .findMostCommonStartingLetterInWordList()
            .findEmployeeWithLongestConsecutiveServicePeriod()
            .findMedianSalaryInEmployeeList()
            .findSpamMessageBasedOnKeyWordFrequency()
            .findKthMostExpansiveItemFromInventoryList()
            .findOutliersInNumericalData()
            .findOverlappingTimeSlotInSchedulesList()
            .findUniqueBigramsInString()
            .findTop3WordsInParagraph()
            .findCustomersWithPurchasesInConsecutiveMonths()
        ;
    }
}
