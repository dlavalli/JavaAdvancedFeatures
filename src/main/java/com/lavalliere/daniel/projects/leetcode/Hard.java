package com.lavalliere.daniel.projects.leetcode;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

@IsDemoable
public class Hard implements Demoable {

    public boolean isMatch(String s, String p) {

        // No match if no more pattern but string not complete
        if (p.isEmpty()) return s.isEmpty();

        // Does the st character match the current pattern character
        boolean matchFirstChar = (!s.isEmpty() &&  (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));

        // If have 0 or more occurrences of a char
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return (
                isMatch(s, p.substring(2)) ||  // Have a match Skipping current n* ?
                (matchFirstChar && isMatch(s.substring(1), p))  // If first char match, advance string by one and test
            );

        // If not 0 or more occurrences of a char
        } else {
            return (
                // If first char match, advance by one both string and pattern and test
                matchFirstChar && isMatch(s.substring(1), p.substring(1))
            );
        }
    }

    private Hard testExpressionMatch() {

        // True Set
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "aa", "a*", isMatch("aa", "a*")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "a", "a.*", isMatch("a", "a.*")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "add", "a.*", isMatch("add", "a.*")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "a", ".*", isMatch("a", ".*")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "aa", ".*", isMatch("aa", ".*")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "ab", ".*", isMatch("ab", ".*")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "aaa", "ab*ac*a", isMatch("aaa", "ab*ac*a")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "abacca", "ab*ac*a", isMatch("abacca", "ab*ac*a")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "abacca", "ab*ac*a", isMatch("abacca", "ab*a*c*a")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "aab", "c*a*b*", isMatch("aab", "c*a*b*")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "aaa", "a*a", isMatch("aaa", "a*a")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "aaa", "ab*a*c*a", isMatch("aaa", "ab*a*c*a")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "aaa", "a*b*ac*a", isMatch("aaa", "a*b*ac*a")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "", ".*", isMatch("", ".*")));

        // False Set
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "baa", "a*", isMatch("baa", "a*")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "aaa", "c*a*b", isMatch("aaa", "c*a*b")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "abacca", "ab*aca", isMatch("abacca", "ab*aca")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "ab", ".*c", isMatch("ab", ".*c")));
        System.out.println(String.format("Match for String: %s pattern:%s if %b", "aaac", "a*b*ac*a", isMatch("aaac", "a*b*ac*a")));


        return this;
    }

    @Override
    public void demo() {
        new Hard()
            .testExpressionMatch()
        ;
    }
}
