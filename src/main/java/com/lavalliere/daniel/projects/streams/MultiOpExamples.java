package com.lavalliere.daniel.projects.streams;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;
import com.sun.source.tree.Tree;

import javax.swing.plaf.multi.MultiButtonUI;
import java.security.InvalidParameterException;
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

    public record Transaction (LocalDate date, double amount) {}

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
            new Transaction(LocalDate.of(2023, 10, 1), 100.0),
            new Transaction(LocalDate.of(2023, 10, 1), 200.0),
            new Transaction(LocalDate.of(2023, 9, 15), 150.0),
            new Transaction(LocalDate.of(2023, 9, 15), 50.0)
        ).sorted(Comparator.comparing(Transaction::date)
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
        Map.Entry entry =Stream.of(
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
            new Transaction(LocalDate.of(2023, 1, 15), 100.0),
            new Transaction(LocalDate.of(2023, 1, 20), 200.0),
            new Transaction(LocalDate.of(2023, 2, 10), 150.0),
            new Transaction(LocalDate.of(2023, 2, 25), 300.0),
            new Transaction(LocalDate.of(2023, 3, 5), 250.0)
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
        ;
    }
}
