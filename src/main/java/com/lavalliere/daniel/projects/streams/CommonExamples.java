package com.lavalliere.daniel.projects.streams;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@IsDemoable
public class CommonExamples implements Demoable {

    public record Employee(String name, String department, double salary, LocalDate joiningDate, int age) {
        @Override
        public int hashCode() {
            return name.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return name.equals(((Employee) obj).name);
        }
    }

    public record Person (String name, int age) {}

    public CommonExamples createStreamFromArray() {
        System.out.println("Stream from array");
        int[] arr = {1, 2, 3, 4, 5, 6};
        IntStream intStream = Arrays.stream(arr); // LongStream, DoubleStream
        // IntStream.builder().add(8).build();
        intStream.forEach(System.out::println);
        return this;
    }

    public CommonExamples listToStream() {
        System.out.println("Stream from list");
        // List.of(10, 20, 30, 40, 50, 60).stream()
        Stream.of(10, 20, 30, 40, 50, 60)
            .forEach(System.out::println);
        return this;
    }

    public CommonExamples inifiniteStreamEvenNumbers() {
        System.out.println("Stream from infinite even numbers");
        // Stream.iterate(0, i -> i + 2).limit(5).forEach(System.out::println);
        Stream<Integer> evenIntStream = Stream.iterate(0, i -> i + 2); // The infinite even number stream
        evenIntStream.limit(5).forEach(System.out::println);
        return this;
    }

    public CommonExamples randomIntStream() {
        System.out.println("Stream from random integers");
        Random random = new Random();
        Stream<Integer> randomIntStream = Stream.generate(random::nextInt);
             // Stream.generate(() -> random.nextInt());
        randomIntStream.limit(5).forEach(System.out::println);
        return this;
    }

    public CommonExamples setToStream() {
        System.out.println("Stream from set to stream");
        Set.of(1, 2, 3, 4, 5, 6).stream()
            // .sorted()
            .forEach(System.out::println);
        return this;
    }

    public CommonExamples streamFromNumberRange() {
        System.out.println("Stream range 1 to 100");
        Random random = new Random();
        // Stream.iterate(1, i -> i + 1).limit(100).forEach(System.out::println);
        // IntStream.range(1, 100).limit(5).forEach(System.out::println);
        // DoubleStream.iterate(1.0, d -> d + 1.0).limit(5).forEach(System.out::println);  // No range on Double
        // DoubleStream.of(1.2,4.5,7.3,40.5).forEach(System.out::println);
        RandomGenerator.getDefault().doubles(1.0, 100.0).limit(5).forEach(System.out::println);
        // DoubleStream.generate(() -> Math.random() * 100).limit(10).forEach(System.out::println);             // NOT correct since all values > = 10
        // DoubleStream.generate(random::nextDouble).map(d -> d * 100).limit(10).forEach(System.out::println);  // NOT correct since all values > = 10
        return this;
    }

    public CommonExamples mapKeysToStream() {
        System.out.println("Stream from map keys to stream");
        Map.of("one", 1, "two", 2, "three", 3, "four", 4, "five", 5,
            "six", 6, "seven", 7 , "eight", 8, "nine", 9, "ten", 10)
            .keySet().stream().limit(5).forEach(System.out::println);
//        Stream.of(
//            Map.entry("one", 1),
//            Map.entry("two", 2),
//            Map.entry("three", 3),
//            Map.entry("four", 4),
//            Map.entry("five", 5),
//            Map.entry("six", 6),
//            Map.entry("seven", 7),
//            Map.entry("eight", 8),
//            Map.entry("nine", 9),
//            Map.entry("ten", 10)
//        ).map(Map.Entry::getKey).limit(5).forEach(System.out::println);
//        Stream.of(Map.ofEntries(
//            Map.entry("one", 1),
//            Map.entry("two", 2),
//            Map.entry("three", 3),
//            Map.entry("four", 4),
//            Map.entry("five", 5),
//            Map.entry("six", 6),
//            Map.entry("seven", 7),
//            Map.entry("eight", 8),
//            Map.entry("nine", 9),
//            Map.entry("ten", 10)
//        )).flatMap(map -> map.keySet().stream()).limit(5).forEach(System.out::println);
        return this;
    }

    public CommonExamples mapValuesToStreamSum() {
        System.out.println("Stream from map values to stream and sum");
        int sum = Map.of("one", 1, "two", 2, "three", 3, "four", 4, "five", 5,
            "six", 6, "seven", 7 , "eight", 8, "nine", 9, "ten", 10)
            .values().stream().mapToInt(Integer::intValue).sum();
//        int sum = Map.of("one", 1, "two", 2, "three", 3, "four", 4, "five", 5,
//            "six", 6, "seven", 7 , "eight", 8, "nine", 9, "ten", 10).entrySet().stream()
//            .peek(entry -> System.out.println("Key " + entry.getKey() + " value: " + entry.getValue()))
//            .mapToInt(Map.Entry::getValue).sum();
        System.out.println("sum = " + sum);
        return this;
    }

    private Path getSampleFile(String fileName) {
        Path path = null;
        try {
            URL resource = this.getClass().getResource("/" + fileName);
            if (resource == null) {
                throw new IllegalArgumentException("File not found!");
            }
            URI resourceUri = resource.toURI();
            path = Paths.get(resourceUri);
       } catch (URISyntaxException | IllegalArgumentException ex) {}
       return path;
    }

    public CommonExamples streamFromFileLines() {
        System.out.println("Stream from file lines");
        Path path = getSampleFile("Sample1.txt");
        if (path == null) {
            throw new RuntimeException("Unable to locate file Sample1.txt");
        }

        try (Stream<String> lines = java.nio.file.Files.lines(path, StandardCharsets.UTF_8)) {
            lines.limit(5).forEach(System.out::println);
        } catch (Exception ex) {
            throw new RuntimeException("Unable to read file Sample1.txt", ex);
        }

        return this;
    }

    public CommonExamples stringToStreamCountVowels() {
        String vowels = "aeiouAEIOU";
        System.out.println("Stream from string to vowels count");
        long vowelCount = "The quick brown fox jumps over the lazy dog".chars()
            .filter(c -> vowels.indexOf(c) != -1)
            .peek(c ->System.out.println("A vowel: " + (char)c))
            .count();  // No y vowel in english
        System.out.println("vowelCount = " + vowelCount);
        return this;
    }

    public CommonExamples stringToStreamCountUnicodeEmoji() {
        String vowels = "aeiouAEIOU";
        System.out.println("Stream from unicode string to emoji count");
        long vowelCount = "The quick brown fox 😊 jumps over 🤣 the lazy dog 😒".codePoints()
            .filter(Character::isEmoji)
            .peek(c ->System.out.println("An emoji: " + Character.toString(c)))
            .count();
        System.out.println("emojiCount = " + vowelCount);
        return this;
    }

    public CommonExamples filterOutEvenNumbersStream() {
        System.out.println("Stream filter out even numbers");
        Stream.of(1,2,3,4,5,6,7,8,9,10).filter(n -> n % 2 != 0).forEach(System.out::println);
        return this;
    }

    public CommonExamples filterStreamOfStringStartingWithA() {
        System.out.println("Stream filter strings starting with A");
        List<String> match =Stream.of("Apple","Banana", "Avocado", "Apricot", "Orange")
            .filter(str -> str.startsWith("A"))
            .collect(Collectors.toList());
        System.out.println("match = " + match);
        return this;
    }

    public CommonExamples removeAllNullValuesFromList() {
        System.out.println("Remove all null values from a list");
        // List.of(...);  // Does NOT allow null values . ie: @NotNull annotated for the args
        List<String> nonNullList = Arrays.asList("Apple", null, "Banana", null, "Cherry", null).stream().filter(Objects::nonNull).toList();
        System.out.println("nonNullList = " + nonNullList);
        return this;
    }

    public CommonExamples convertListOfStringsToUpperCase() {
        System.out.println("Convert list of strings to upper case");
        // List<String> upperCaseList = Arrays.asList("Apple", "Banana", "Cherry").stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> upperCaseList = Arrays.asList("Apple", "Banana", "Cherry").stream().map(String::toUpperCase).toList();
        System.out.println("upperCaseList = " + upperCaseList);
        return this;
    }

    public CommonExamples convertListOfInterToTheirSquare() {
        System.out.println("Convert list of integers to their square");
        List<Integer> squaredList = Arrays.asList(1, 2, 3, 4, 5).stream().map(n -> n * n).toList();
        System.out.println("squaredList of [1,2,3,4,5] = " + squaredList);
        return this;
    }

    public CommonExamples extractDomainNameFromEmailAddress() {
        System.out.println("Extract domain name from email address");
        Stream.of("user1@example.com","user2@domain.com","user3@test.com", "user4@example.com")
            .map(email -> email.substring(email.indexOf("@") + 1))
            .distinct()
            .forEach(System.out::println);
        return this;
    }

    public CommonExamples filterStringsWithMoreThan5Characters() {
        System.out.println("Filter strings with more than 5 characters");
        Stream.of("Alice", "Bob", "Charlie", "Daniel", "Eve", "Franklin")
            .filter(str -> str.length() > 5)
            .forEach(System.out::println);
        return this;
    }

    public CommonExamples convertListOfWordsToLengths() {
        System.out.println("Convert list of strings to their length");
        List<Integer> wordLengths = Stream.of("apple","banana", "cherry", "date")
            .mapToInt(String::length)
            .boxed() // Converts primitive to wrapper type from IntStream
            .toList();
        System.out.println("word lengths = " + wordLengths);
        return this;
    }

    public CommonExamples convertListOfWordsToLengthsAndSum() {
        System.out.println("Convert list of strings to their length and sum");
        int totalLength = Stream.of("apple","banana", "cherry", "date")
            .peek(word -> System.out.println(word + " Length: " + word.length()))
            .mapToInt(String::length)
            .sum();
        System.out.println("totalLength = " + totalLength);
        return this;
    }

    public CommonExamples extractUniqueCharactersFromWordList() {
        System.out.println("Extract unique characters from a list of words");
        List<Character> uniqueChars = Stream.of("apple","banana", "cherry")
            .flatMap(word -> word.chars().mapToObj(c -> (char) c))// Convert stream of strings string of Character
            .distinct()
            .toList();
        System.out.println("uniqueChars = " + uniqueChars);
        return this;
    }

    public CommonExamples replaceAllOccurencesOfSubstringInWordList() {
        System.out.println("Replace all occurrences of a substring in a list of words");
        List<String> replacedWords = Stream.of("apple","banana", "cherry", "date")
            .map(word -> word.replace("a", "***"))
            .toList();
        System.out.println("replacedWords = " + replacedWords);
        return this;
    }

    public CommonExamples flattenListOfLIstsIntoSingleList() {
        System.out.println("Flatten list of LIsts into single list");
        List<Integer> listOfLists =
            Stream.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(5, 6),
                List.of(7, 8)
            ).flatMap(List::stream).toList(); // Flatten multiple streams into a single one, calling stream in each list elements
        System.out.println("listOfLists = " + listOfLists);
        return this;
    }

    public CommonExamples splitSentenceIntoWordsAndRemoveDuplicates() {
        System.out.println("Splitting sentence into single list");
        List<String> words = Stream.of("hello world hello me".split(" "))
            .distinct()
            .toList();
        System.out.println("words = " + words);
        return this;
    }

    public CommonExamples flattenListOfArraysIntoSingleStreamWithoutDuplicates() {
        System.out.println("Flatten list of arrays into single Stream");
        Stream<Integer> flattenStream = Stream.of(new int[]{1, 2}, new int[]{3, 4},new int[]{5, 6}, new int[]{2, 6})
            .flatMapToInt(Arrays::stream) // Convert Stream of int[] to IntStream
            .distinct()
            .boxed(); // Convert IntStream to Stream<Integer>

        flattenStream.forEach(System.out::println);
        return this;
    }

    public CommonExamples convertListOfSentencesIntoListOfWords() {
        System.out.println("Convert list of sentences into list of words");
        List<String> words = Stream.of("Hello world", "Java Streams", "FlatMap example")
            .map(sentence -> sentence.split(" "))
            .flatMap(Arrays::stream)
            .toList();
        System.out.println("words = " + words);
        return this;
    }

    public CommonExamples extractUniqueNumbersFromListOfIntegerArrays() {
        System.out.println("Extract unique numbers from a list of Integer");
        Stream.of(
            List.of(1, 2),
            List.of(3, 4),
            List.of(5, 6),
            List.of(7, 8)
        ).flatMap(List::stream)
         .distinct()
         .forEach(System.out::println);
        return this;
    }

    public CommonExamples sortListOfIntegerInAscendingOrder() {
        System.out.println("Sort list of integers in ascending order");
        Stream.of(5,3,9,1,7,3)
            .distinct()
            .sorted()
            .forEach(System.out::println);
        return this;
    }

    public CommonExamples sortListOfStringByLength() {
        System.out.println("Sort list of strings by length");
        List<String> words = Stream.of("apple", "banana", "cherry", "date")
            .sorted(Comparator.comparingInt(String::length))
            .toList();
        System.out.println("words = " + words);
        return this;
    }

    public CommonExamples sortListOfListOfStringByLength() {
        System.out.println("Sort list of list of strings by length");
        List<String> words = Stream.of(List.of("apple", "banana"), List.of("cherry", "date"))
            .flatMap(List::stream)
            .sorted(Comparator.comparingInt(String::length))
            .toList();
        System.out.println("words = " + words);
        return this;
    }

    public CommonExamples sortEmployeeListBySalaryWithoutDuplicates() {
        List<Employee> employees = Stream.of(
            new Employee("Alice", "Accounting", 50000, LocalDate.of(2026,1,1), 30),
            new Employee("Bob", "IT", 60000, LocalDate.of(2026,1,1), 30),
            new Employee("Charlie", "HR", 45000, LocalDate.of(2026,1,1), 30),
            new Employee("David", "Development", 55000, LocalDate.of(2026,1,1), 30),
            new Employee("Bob", "IT", 56000, LocalDate.of(2026,1,1), 30)
        ).distinct()  // distinct used equals() and hashcode() to compare objects, so we need to override them in the record
         .sorted(Comparator.comparing(Employee::salary))
         .toList();
        System.out.println("employees = " + employees);
        return this;
    }

    public CommonExamples sortNumbersInDescendingOrderNoDuplicate() {
        Stream.of(5,3,9,1,7,5,3)
            .distinct()
            .sorted(Comparator.reverseOrder())
            .forEach(System.out::println);
        return this;
    }

    public CommonExamples sortObjectListByMultipleConditions() {
        System.out.println("Sort list of objects by multiple conditions");
        List<Employee> employees = Stream.of(
            new Employee("Alice", "HR", 50000, LocalDate.of(2026,1,1), 30),
            new Employee("Bob", "IT",    60000, LocalDate.of(2026,1,1), 30),
            new Employee("Charlie", "HR", 45000, LocalDate.of(2026,1,1), 30),
            new Employee("David", "IT", 55000, LocalDate.of(2026,1,1), 30),
            new Employee("Eve", "Finance", 70000, LocalDate.of(2026,1,1), 30),
            new Employee("Bob", "IT", 56000, LocalDate.of(2026,1,1), 30)
        ).distinct()
            // NOTE: chained .sorted().sorted() approach completely overwrites the previous
            //       sorting order because the final sort takes full priority over the previous one.
            //       So only the last sort will be applied to the stream.
         .sorted(Comparator.comparing(Employee::department)
                 .thenComparing(Comparator.comparing(Employee::salary).reversed()))
         .toList();
        System.out.println("employees = " + employees);
        return this;
    }

    public CommonExamples printEachElementWhileProcessingAStream() {
        System.out.println("printEachElementWhileProcessingAStream");
        List<Integer> numbers = Stream.of(1,2,3,4,5)
            .peek(n -> System.out.println("Processing number: " + n))
            .filter(n -> n % 2 == 0)
            .peek(n -> System.out.println("Filtered Even number: " + n))
            .map(n -> n * n)
            .peek(n -> System.out.println("Squared number: " + n))
            .toList();
        System.out.println("numbers = " + numbers);
        return this;
    }

    public CommonExamples logFilteringAndMappingInStream() {
        System.out.println("logFilteringAndMappingInStream");
        List<String> words = Stream.of("apple", "banana", "cherry", "date")
            .peek(word -> System.out.println("Original word: " + word))
            .filter(word -> word.length() > 5)
            .peek(word -> System.out.println("Filtered word (length > 5): " + word))
            .map(String::toUpperCase)
            .peek(word -> System.out.println("Mapped to upper case: " + word))
            .toList();
        System.out.println("words = " + words);
        return this;
    }

    public CommonExamples printElementsOrderInSortedStream() {
        System.out.println("printElementsOrderInSortedStream");
        List<Integer> numbers = Stream.of(5, 3, 5, 1, 7)
            .distinct()
            .sorted()
            .peek(System.out::println)
            .toList();
        System.out.println("numbers = " + numbers);
        return this;
    }

    public CommonExamples printElementsAsTheyAreFileredOut() {
        List<Integer> numbers = Stream.of(5, 3, 2, 5, 4, 1, 7, 8)
            .distinct()
            .peek(n -> {
                System.out.println("Processing number: " + n);
            })
            .filter(n -> {
                boolean isEven = n % 2 == 0;
                if (isEven) {
                    System.out.println("Filtering out even number: " + n);
                }
                return !isEven;
            })  // Elements meeting the predicate remains
            .toList();
            System.out.println("numbers = " + numbers);
            return this;
    }

    public CommonExamples printFirst3ElementsOfList() {
        System.out.println("printFirst3ElementsOfList");
        List<Integer> numbers = Stream.of(1,2,3,4,5).limit(3).toList();
        System.out.println("numbers = " + numbers);
        return this;
    }

    public CommonExamples skipFirst5ElementsAndPrintRest() {
        System.out.println("skipFirst5ElementsAndPrintRest");
        List<Integer> numbers = Stream.of(1,2,3,4,5,6,7,8,9,10)
            .peek(n -> System.out.println("Processing number: " + n))
            .skip(5)
            .toList();
        System.out.println("Remaining numbers = " + numbers);
        return this;
    }

    public CommonExamples getTop3SalariesFromEmployeeList() {
        System.out.println("getTop3SalariesFromEmployeeList");
        List<Employee> employees = Stream.of(
            new Employee("Alice", "HR", 50000, LocalDate.of(2026,1,1), 30),
            new Employee("Bob", "IT",    60000, LocalDate.of(2026,1,1), 30),
            new Employee("Charlie", "HR", 45000, LocalDate.of(2026,1,1), 30),
            new Employee("David", "IT", 55000, LocalDate.of(2026,1,1), 30),
            new Employee("Eve", "Finance", 70000, LocalDate.of(2026,1,1), 30),
            new Employee("Bob", "IT", 56000, LocalDate.of(2026,1,1), 30)
        ).distinct()
         .sorted(Comparator.comparing(Employee::salary).reversed())
         .limit(3)
         .toList();
        System.out.println("Top 3 Salary employees = " + employees);
        return this;
    }

    public CommonExamples printLast5ElementsOfList() {
        System.out.println("printLast5ElementsOfList");
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);
        List<Integer> last5 = numbers.stream()
            .skip(numbers.size() - 5)
            .toList();
        System.out.println("Last 5 numbers = " + numbers);
        return this;
    }

    public CommonExamples skipEvenNumbersFromListAndPrintRest() {
        System.out.println("skipEvenNumbersFromListAndPrintRest");
        Stream.of(5,2,4,1,8,6,3,7,10,9)
            .filter(n -> n % 2 != 0)
            .forEach(n -> System.out.println("Processing number: " + n));
        return this;
    }

    public CommonExamples printAllElementsUsingForEach() {
        System.out.println("printAllElementsUsingForEach");
        Stream.of(1, 2, 3, 4, 5).forEach(System.out::println);
        return this;
    }

    public CommonExamples printKeyValuePairsOfMap() {
        System.out.println("printKeyValuePairsOfMap");
        Map.of("one", 1, "two", 2, "three", 3, "four", 4, "five", 5)
            .entrySet().stream()
            // .sorted(Map.Entry.comparingByKey())
            .forEach(System.out::println);
        return this;
    }

    public CommonExamples printElementsInReverseOrder() {
        System.out.println("printElementsInReverseOrder");
        List.of(9,10,8,7,6,5,4,3,2,1).reversed().stream().forEachOrdered(System.out::println);  // forEachOrdered requested in problem but no reason to use here since not parallel

        // Can use the following BUT wouldactually reverse of sorted not just reversed
        // Stream.of(9,10,8,7,6,5,4,3,2,1)
        //     .sorted(Comparator.reverseOrder())
        //     .forEachOrdered(System.out::println);  // Request in statement of problem BUT only meaningful for parallel streams

        /*
            Feature                 forEach()                       forEachOrdered
            Sequential stream       Process elements in order       Process elements in order
            Parallel stream         Non-deterministic, order is     Deterministic; strictly preserve
                                    not guaranteed                  source order
            Performance overhead    Minimal; highly efficient       High in parallel streams due to
                                                                    thread coordination
         */
        return this;
    }

    public CommonExamples printEvenIndexedListElements() {
        System.out.println("printEvenIndexedListElements");
        List<Integer> numbers = List.of(9, 10,11,12,13,14,15,16,17,18,19,20);
        IntStream.range(0, numbers.size())
            .filter(n -> n % 2 == 0)
            .mapToObj(numbers::get)
            .forEach(System.out::println);
        return this;
    }

    public CommonExamples printUniqueElementWithSet() {
        System.out.println("printUniqueElementWithSet");

        // Problem statement state using HashSet. List required to pass duplicates in test
        List<Integer> numbers = new HashSet<>(List.of(1, 2, 2, 3, 4, 4, 5)).stream()
            .toList();

        // Better way of doing this
        // List<Integer> numbers = Stream.of(1, 2, 2, 3, 4, 4, 5).distinct().toList();
        System.out.println("Unique numbers are: " + numbers);

        return this;
    }

    public CommonExamples sumNumbersInListWithReduce() {
        System.out.println("sumNumbersInListWithReduce");
        int sum = Stream.of(1, 2, 3, 4, 5)
            // .reduce(0, (n, m) -> n + m);
            .reduce(0, Integer::sum); // Identical to previous. Will sum 0 with 1, then the sum with 2 then that sum with 3 etcc

        System.out.println("sum of numbers= " + sum);
        return this;
    }

    public CommonExamples productOfNumbersInListWithReduce() {
        System.out.println("productOfNumbersInListWithReduce");
        int product = Stream.of(1, 2, 3, 4, 5)
            .reduce(1, (n, m) -> n * m); // No Integer method for product, start at 1 else value always 0
        System.out.println("product of numbers= " + product);
        return this;
    }

    public CommonExamples longestWordInStringListWithReduce() {
        System.out.println("longestWordInStringListWithReduce");
        int longestWord = Stream.of("apple","bananas", "cherry", "date")
            .map(String::length)
            .reduce(0, Integer::max);
        System.out.println("longest word= " + longestWord);
        return this;
    }

    public CommonExamples smallestNumberInListWithReduce() {
        System.out.println("smallestNumberInListWithReduce");
        int minNumber = Stream.of(21, 13, 3, 37, 9)
            .reduce(Integer.MAX_VALUE, Integer::min);
        System.out.println("smallest number= " + minNumber);
        return this;
    }
    public CommonExamples factorialOfNumberInWithReduce() {
        System.out.println("factorialOfNumberInWithReduce");
        // int factorial = IntStream.range(1,6)  // [1, 6[
        int factorial = IntStream.rangeClosed(1,5)  // [1, 5]
            .reduce(1, (x, y) -> x * y);
        System.out.println("factorial= " + factorial);
        return this;
    }

    public CommonExamples convertListOfStringsToSetWithCollect() {
        System.out.println("convertListOfStringsToSetWithCollect");
        Set<String> words = Stream.of("apple","banana", "cherry", "apple")
            // .distinct() // Not requires, silently handles by toSet
            .collect(Collectors.toSet());
        System.out.println("words= " + words);
        return this;
    }

    public CommonExamples convertNumberStreamToArray() {
        System.out.println("convertNumberStreamToArray");

        // Expecting Integer[] not int[]
        // int[] numberArray = Stream.of(1,2,3,4,5)
        //     .mapToInt(Integer::intValue)  // Produces int[] instead of Integer[]
        //     .toArray();

        // Longer version using Stream.of
        // Integer[] numberArray = Stream.of(1,2,3,4,5)
        //     .mapToInt(Integer::intValue)
        //     .boxed()
        //     .toArray(Integer[]::new);

        // Shorter version using IntStream.of
        Integer[] numberArray = IntStream.of(1,2,3,4,5)
            .boxed()
            .toArray(Integer[]::new);

        System.out.println("numberArray= " + Arrays.toString(numberArray));
        return this;
    }

    public CommonExamples groupWordsByFirstLetter() {
        System.out.println("groupWordsByFirstLetter");
        Map<Character, List<String>> groups = Stream.of("apple","banana", "cherry", "date", "avocado")
            .collect(Collectors.groupingBy(word -> word.charAt(0)));
        System.out.println("groups= " + groups);
        return this;
    }

    public CommonExamples partitionIntoEvenAndOddNUmbers() {
        System.out.println("partitionIntoEvenAndOddNUmbers");
        Map<String, List<Integer>> partitions = Stream.of(1,2,3,4,5,6)
            .collect(Collectors.groupingBy(n -> n % 2 == 0 ? "even" : "odd"));
        System.out.println("partitions= " + partitions);
        return this;
    }

    public CommonExamples joinListOfWordsIntoCommaSeparatedString() {
        System.out.println("joinListOfWordsIntoCommaSeparatedString");
        String collected = Stream.of("apple","banana", "cherry")
            .collect(Collectors.joining(","));
        System.out.println("collected= " + collected);
        return this;
    }

    public CommonExamples numberOFElementsInListWithCount() {
        System.out.println("numberOFElementsInListWithCount");
        long count = Stream.of(1,2,3,4,5,6).count();
        System.out.println("Elements count= " + count);
        return this;
    }

    public CommonExamples shortestStringInListWithStream() {
        System.out.println("shortestStringInListWithStream");
          // Handle Optional returned by reduce
        Optional<String> shortest = Stream.of("apple","banana", "cherry", "date")
                .reduce((m, n) -> m.length() <= n.length() ? m : n);

        System.out.println("shortest= " + shortest.orElse("[]"));
        return this;
    }
    public CommonExamples largestNumberInListWithReduce() {
        System.out.println("largestNumberInListWithReduce");
        int maxNumber = Stream.of(21, 13, 3, 37, 9)
            .reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println("largest number= " + maxNumber);
        return this;
    }

    public CommonExamples countDistinctElementsInListWithStream() {
        System.out.println("countDistinctElementsInListWithStream");
        long count = IntStream.of(1,2,2,3,4,4,5)
            .distinct()
            .count();
        System.out.println("distinct elements count= " + count);
        return this;
    }

    public CommonExamples secondHighestSalaryFromList() {
        System.out.println("secondHighestSalaryFromList");
        Integer number = Stream.of(50000, 60000, 45000, 55000, 70000)
            .sorted(Comparator.comparing(Integer::intValue).reversed())
            .skip(1)
            .findFirst()
            .orElse(null);  // NOTE: .orElse is part of Optional() not Stream/Collectors
        System.out.println("secondHighestSalaryFromList= " + number);
        return this;
    }

    public CommonExamples firstEvenNumberInList() {
        System.out.println("firstEvenNumberInList");
        int firstEvenNumber = IntStream.of(1, 3, 5, 2, 4, 6)
            .filter(n -> n % 2 == 0)
            .findFirst()
            .orElse(0); // NOTE: .orElse is part of Optional() not Stream/Collectors
        System.out.println("firstEvenNumber= " + firstEvenNumber);
        return this;
    }

    public CommonExamples findAnyElementInList() {
        System.out.println("findAnyElementInList");
        String anyFound = Stream.of("apple", "banana", "cherry")
            .findAny()
            .orElse(null);  // NOTE: .orElse is part of Optional() not Stream/Collectors
        System.out.println("any element Found: " + anyFound);
        return this;
    }

    public CommonExamples findFirstPersonOlderThan25InList() {
        System.out.println("findFirstPersonOlderThan25InList");
        Person match = Stream.of(
            new Person("Alice", 22),
            new Person("Bob", 30),
            new Person("Charlie", 28),
            new Person("David", 20)
        ).filter(person -> person.age > 25)
            .findFirst()
            .orElse(null);  // NOTE: .orElse is part of Optional() not Stream/Collectors
        System.out.println("findFirstPersonOlderThan25InList= " + match);
        return this;
    }

    public CommonExamples firstWordStartingWithB() {
        System.out.println("firstWordStartingWithB");
        String match = Stream.of("apple", "banana", "cherry", "blueberry")
            .filter(element -> element.startsWith("b"))
            .findFirst()
            .orElse(null);  // NOTE: .orElse is part of Optional() not Stream/Collectors
        System.out.println("firstWordStartingWithB= " + match);
        return this;
    }

    public CommonExamples findAnyEmployeeFromAList() {
        System.out.println("findAnyEmployeeFromAList");
        Employee employee = Stream.of(
            new Employee("Alice", "Accounting", 50000, LocalDate.of(2026,1,1), 30),
            new Employee("Bob", "IT", 60000, LocalDate.of(2026,1,1), 30),
            new Employee("Charlie", "HR", 45000, LocalDate.of(2026,1,1), 30),
            new Employee("David", "Development", 55000, LocalDate.of(2026,1,1), 30),
            new Employee("Bob", "IT", 56000, LocalDate.of(2026,1,1), 30)
        ).findAny()
         .orElse(null);  // NOTE: .orElse is part of Optional() not Stream/Collectors
        System.out.println("findAnyEmployeeFromAList= " + employee);
        return this;
    }

    public CommonExamples checkIfAnyNumberNegativeInList() {
        System.out.println("checkIfAnyNumberNegativeInList");
        boolean hasNegative = IntStream.of(1 ,2, -3, 4, 5)
            .anyMatch(n -> n < 0);
        System.out.println("checkIfAnyNumberNegativeInList= " + hasNegative);
        return this;
    }

    public CommonExamples checkIfAllPositiveNumberInList() {
        System.out.println("checkIfAllPositiveNumberInList");
        boolean allPositive = IntStream.of(1 ,2, 3, 4, 5)
            .allMatch(n -> n > 0);
        System.out.println("checkIfAllPositiveNumberInList= " + allPositive);
        return this;
    }

    public CommonExamples check0NotInNumberList() {
        System.out.println("check0NotInNumberList");
        boolean zeroFound = IntStream.of(1,2,3,4,5,0)
            .noneMatch(n -> n == 0);
        System.out.println("check0NotInNumberList= " + zeroFound);
        return this;
    }

    public CommonExamples checkAllWordsHaveAtLeast4Letters() {
        System.out.println("checkAllWordsHaveAtLeastLetters");
        boolean match = Stream.of("apple", "banana", "cherry", "egg", "david")
            .allMatch(word -> word.length() >= 4);
        System.out.println("checkAllWordsHaveAtLeast4Letters= " + match);
        return this;
    }

    public CommonExamples checkAnyNameInListContainsxyz() {
        System.out.println("checkAnyNameInListContainsxyz");
        boolean isMatch = Stream.of("Alice", "Bob", "Charlie", "Dexter")
            .anyMatch(word -> word.contains("ext"));
        System.out.println("checkAnyNameInListContainsxyz= " + isMatch);

        return this;
    }

    @Override
    public void demo() {
        System.out.println("Stream demo");
        new CommonExamples()
            .createStreamFromArray()
            .listToStream()
            .inifiniteStreamEvenNumbers()
            .randomIntStream()
            .setToStream()
            .setToStream()
            .streamFromNumberRange()
            .mapKeysToStream()
            .mapValuesToStreamSum()
            .streamFromFileLines()
            .stringToStreamCountVowels()
            .stringToStreamCountUnicodeEmoji()
            .filterOutEvenNumbersStream()
            .filterStreamOfStringStartingWithA()
            .removeAllNullValuesFromList()
            .convertListOfStringsToUpperCase()
            .convertListOfInterToTheirSquare()
            .extractDomainNameFromEmailAddress()
            .filterStringsWithMoreThan5Characters()
            .convertListOfWordsToLengths()
            .convertListOfWordsToLengthsAndSum()
            .extractUniqueCharactersFromWordList()
            .replaceAllOccurencesOfSubstringInWordList()
            .flattenListOfLIstsIntoSingleList()
            .splitSentenceIntoWordsAndRemoveDuplicates()
            .flattenListOfArraysIntoSingleStreamWithoutDuplicates()
            .convertListOfSentencesIntoListOfWords()
            .extractUniqueNumbersFromListOfIntegerArrays()
            .sortListOfIntegerInAscendingOrder()
            .sortListOfStringByLength()
            .sortListOfListOfStringByLength()
            .sortEmployeeListBySalaryWithoutDuplicates()
            .sortNumbersInDescendingOrderNoDuplicate()
            .sortObjectListByMultipleConditions()
            .logFilteringAndMappingInStream()
            .printElementsOrderInSortedStream()
            .printElementsAsTheyAreFileredOut()
            .printFirst3ElementsOfList()
            .skipFirst5ElementsAndPrintRest()
            .getTop3SalariesFromEmployeeList()
            .printLast5ElementsOfList()
            .skipEvenNumbersFromListAndPrintRest()
            .printAllElementsUsingForEach()
            .printKeyValuePairsOfMap()
            .printElementsInReverseOrder()
            .printEvenIndexedListElements()
            .printUniqueElementWithSet()
            .sumNumbersInListWithReduce()
            .productOfNumbersInListWithReduce()
            .longestWordInStringListWithReduce()
            .smallestNumberInListWithReduce()
            .factorialOfNumberInWithReduce()
            .convertListOfStringsToSetWithCollect()
            .convertNumberStreamToArray()
            .groupWordsByFirstLetter()
            .partitionIntoEvenAndOddNUmbers()
            .joinListOfWordsIntoCommaSeparatedString()
            .numberOFElementsInListWithCount()
            .shortestStringInListWithStream()
            .largestNumberInListWithReduce()
            .countDistinctElementsInListWithStream()
            .secondHighestSalaryFromList()
            .firstEvenNumberInList()
            .findAnyElementInList()
            .findFirstPersonOlderThan25InList()
            .firstWordStartingWithB()
            .findAnyEmployeeFromAList()
            .checkIfAnyNumberNegativeInList()
            .checkIfAllPositiveNumberInList()
            .check0NotInNumberList()
            .checkAllWordsHaveAtLeast4Letters()
            .checkAnyNameInListContainsxyz()
        ;
    }
}

