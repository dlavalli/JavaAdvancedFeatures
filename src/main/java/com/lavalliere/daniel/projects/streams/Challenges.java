package com.lavalliere.daniel.projects.streams;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.sort;

@IsDemoable
    public class Challenges implements Demoable {

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

    public Challenges longestConsecutiveSequenceInArray() {
        System.out.println("Longest Consecutive Sequence In Array: ");
        // IntStream.of(100, 4, 5, 200, 1, 3, 2)
        return this;
    }

    @Override
    public void demo() {
        new Challenges()
            .reverseEachWordsOfAString()
            .isPalindromeStringWithStream()
            .removeDuplicateWordsFromSentence()
            .anagramsInWordLIst()
        ;
    }
}
