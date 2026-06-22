package com.lavalliere.daniel.projects.streams;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.sort;

@IsDemoable
    public class Challenges implements Demoable {

    public enum DaysOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    public Challenges reverseEachWordsOfAString() {
        System.out.println("Reverse Each Words Of A String: ");
        String reversed = Arrays.stream("Saying Hello world to myself !".split(" "))
            .map(word -> new StringBuilder(word).reverse().toString())
                .collect(Collectors.joining(" "));
        System.out.println("Reverse Each Words Of A String: " + reversed);
        return this;
    }

    public Challenges isPalindromeStringWithStream() {
        System.out.println("isPalindromeStringWithStream: ");
        //  ie: reads the exact same way both forwards and backwards
        String input = "madam";
        boolean isPalindrome = IntStream.range(0, input.length() / 2)
            .allMatch(i -> input.charAt(i) == input.charAt(input.length() - 1 - i));
        System.out.println("isPalindromeStringWithStream: " + isPalindrome);
        return this;
    }

    public Challenges removeDuplicateWordsFromSentence() {
        System.out.println("Remove Duplicate Words From Sentence: ");
        String modified = Arrays.stream("hello world hello !".split(" "))
            .distinct()
            .collect(Collectors.joining(" "));
        System.out.println("Remove Duplicate Words From Sentence: " + modified);
        return this;
    }

    public Challenges anagramsInWordLIst() {
        System.out.println("Anagrams In Word List: ");
        Map<String, List<String>> anagrams = Stream.of("listen", "silent", "enlist", "google", "inlets")
                .collect(Collectors.groupingBy(word -> {
                    char[] c = word.toCharArray();
                    Arrays.sort(c);         // Sort in place so cannot combine everything in a single line
                    return new String(c);   // Return the key for which to associate the current value
                }));

        System.out.println("Anagrams In Word List: " + anagrams);  // {eggloo=[google], eilnst=[listen, silent, enlist, inlets]}
        anagrams.forEach((word, list) -> {
            if (list.size() > 1) System.out.println("Anagrams for " + word + " are: " + list);
        });
        return this;
    }

    public Challenges longestConsecutiveSequenceWithoutOrderInArray() {
        System.out.println("longestConsecutiveSequenceWithoutOrderInArray");
        Set<Integer> numbers = Set.of(100, 4, 200, 1, 3, 2);

        // NOTE: Resolution of this has NOTHING to do with streams, only used to determine the max
        int longest = 0;
        for(int num : numbers) {
            if (!numbers.contains(num - 1)) {
                int currentNum = num;
                int currentLongest = 1;

                while(numbers.contains(currentNum + 1)) {
                    currentNum++;
                    currentLongest++;
                }

                longest = Math.max(longest, currentLongest);
            }
        }

        System.out.println("longestConsecutiveSequenceWithoutOrderInArray: " + longest);
        return this;
    }

    public Challenges mergeUnsortedArraysIntoSortedArray() {
        System.out.println("mergeUnsortedArraysIntoSortedArray");
        int[] arr1 = {5, 3, 8};
        int[] arr2 = {1, 7, 2};
        Integer[] merged = Stream.of(arr1,arr2)
            .flatMapToInt(Arrays::stream)
            .boxed()  // Using mapToObj(Integer::new) is deprecated for Integer
            .sorted()
            .peek(num -> System.out.println("A num: " + num))
            .toArray(Integer[]::new);

        // NOTE : Correct way to print the content of the Integer[]
        System.out.println("mergeUnsortedArraysIntoSortedArray: " +  Arrays.toString(merged));
        return this;
    }

    public Challenges fingIndidualCharacterOccurenceCount() {
        System.out.println("fingIndidualCharacterOccurenceCount");
        Map<Character, Long> charsCount = "hello world".chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("fingIndidualCharacterOccurenceCount: " + charsCount);
        return this;
    }

    public Challenges getLastElementOfAList() {
        System.out.println("getLastElementOfAList");
        int lastElement = Stream.of(1,2,3,4,5)
            .reduce((first, second) -> second)
                .orElseThrow(IllegalArgumentException::new);
        System.out.println("getLastElementOfAList: " + lastElement);
        return this;
    }

    public Challenges findDuplicateElementsInArray() {
        int[] arr = {1,2,3,2,4,5,4};

        List<Integer> duplicates = Arrays.stream(arr)
            .boxed()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet().stream()
            .filter(entry -> entry.getValue() > 1)
            .map(Map.Entry::getKey)
            .toList();
        System.out.println("findDuplicateElementsInArray: " + duplicates);
        return this;
    }

    public Challenges sumOfDigitsInNumber() {
        System.out.println("sumOfDigitsInNumber");
        int num = 12345;
        int sum = Integer.toString(num).chars()
            .map(Character::getNumericValue)  // From Char to int digit
            .sum();
        System.out.println("sumOfDigitsInNumber: " + sum);
        return this;
    }

    public Challenges convertArrayIntoSet() {
        System.out.println("convertArrayIntoSet");
        int[] arr = {1, 2, 2, 3, 4, 4, 5};
        Set<Integer> set = Arrays.stream(arr)
            .boxed()
            .collect(Collectors.toSet());
        System.out.println("convertArrayIntoSet: " + set);
        return this;
    }

    public Challenges printElementAtEvenIndex() {
        System.out.println("printElementAtEvenIndex");
        List<Integer> list = Arrays.asList(10, 20, 30,40, 50, 60);
        IntStream.range(0, list.size())
            .filter(i -> i % 2 == 0)
            .boxed()
            .forEach(index -> System.out.println(list.get(index)));
        return this;
    }

    public Challenges removePunctuationFromSentence() {
        System.out.println("removePunctuationFromSentence");
        String cleaned = "Hello, world! How are you ?".chars()
            .filter(c -> Character.isLetterOrDigit(c) || Character.isWhitespace(c))
            .mapToObj(Character::toString)
            .collect(Collectors.joining());
        System.out.println("removePunctuationFromSentence: " + cleaned);
        return this;
    }

    public Challenges sortDateListAscending() {
        System.out.println("sortDateListAscending");
        List<String> sortedDates = Stream.of("2023-10-01", "2023-12-25", "2023-09-15")
            .map(LocalDate::parse)
            .sorted()
            .map(LocalDate::toString)
            .toList();
        System.out.println("sortDateListAscending: " + sortedDates);
        return this;
    }

    public Challenges findLongestCommonPrefixInList() {
        System.out.println("findLongestCommonPrefixInList");
        String commonPrefix = Stream.of("flower", "flow", "flight", "fling")
            .reduce((previous, next) -> { // Reduce while Comparing elements in pairs
                int length = 0;
                while(length < previous.length() && length < next.length() &&
                      previous.charAt(length) == next.charAt(length)) {
                      length++;
                }
                return previous.substring(0, length);
            })
            .orElse(null);
        System.out.println("findLongestCommonPrefixInList : " + commonPrefix);
        return this;
    }

    public Challenges convertEnumToStringList() {
        System.out.println("convertEnumToStringList");
        List<String> dow = Arrays.stream(DaysOfWeek.values()).map(DaysOfWeek::name).toList();
        System.out.println("convertEnumToStringList: " + dow);
        return this;
    }

    public Challenges findNThHighestNumber() {
        System.out.println("findNThHighestNumber");
        int n = 2;
        int nth = Stream.of(10, 20, 30, 40, 50)
            .sorted(Comparator.comparing(Integer::intValue).reversed())
            .skip(n - 1)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
        System.out.println("findNThHighestNumber: " + nth);
        return this;
    }

    @Override
    public void demo() {
        new Challenges()
            .reverseEachWordsOfAString()
            .isPalindromeStringWithStream()
            .removeDuplicateWordsFromSentence()
            .anagramsInWordLIst()
            .longestConsecutiveSequenceWithoutOrderInArray()
            .mergeUnsortedArraysIntoSortedArray()
            .fingIndidualCharacterOccurenceCount()
            .getLastElementOfAList()
            .findDuplicateElementsInArray()
            .sumOfDigitsInNumber()
            .convertArrayIntoSet()
            .printElementAtEvenIndex()
            .removePunctuationFromSentence()
            .sortDateListAscending()
            .findLongestCommonPrefixInList()
            .convertEnumToStringList()
            .findNThHighestNumber()
        ;
    }
}
