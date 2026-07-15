package com.lavalliere.daniel.projects.leetcode.hard;

import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;
import java.util.PriorityQueue;

import java.util.stream.Stream;

@IsDemoable
public class HardSamples_1 implements Demoable {

    /*
        You are given an array of k linked-lists lists,
        each linked-list is sorted in ascending order.

        Merge all the linked-lists into one sorted linked-list and return it.
        Input: lists = [[1,4,5],[1,3,4],[2,6]]
        Output: [1,1,2,3,4,4,5,6]
        Explanation: The linked-lists are:
        [
            1->4->5,
            1->3->4,
            2->6
        ]

        merging them into one sorted linked list:
        1->1->2->3->4->4->5->6
     */

     private class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }




        // Proposed version:
        // 1) Push ALL nodes from all lists into min-heap
        // 2) Pop once through heap and relink in sorted order
        private ListNode mergeKListsAlt(ListNode[] lists) {
            PriorityQueue<ListNode> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.val, b.val)
            );

            // Push every node into heap
            for (ListNode head : lists) {
                ListNode curr = head;
                while (curr != null) {
                    pq.offer(curr);
                    curr = curr.next;
                }
            }

            if (pq.isEmpty()) return null;

            // Build sorted linked list by popping from heap
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;

            while (!pq.isEmpty()) {
                ListNode node = pq.poll();
                tail.next = node;
                tail = tail.next;
            }

            // Important: terminate the final list
            tail.next = null;

            // Skipping temporary original head initially set to val = 0
            // return first node added
            return dummy.next;
        }



     /*
         The real problem is that you should sort this, for example, using a priority queue BUT
         In Java, a PriorityQueue cannot automatically determine the sorting order of your custom ListNode class.
         If you attempt to insert a ListNode into a default PriorityQueue constructor, Java will throw a ClassCastException because ListNode does not implement the Comparable interface.
         To fix this, you must explicitly tell the PriorityQueue how to sort the nodes using one of the following two approaches
         1- Pass a Lambda Comparator to the Constructor
         2- Implement the Comparable Interface in  the ListNode class

         Example: lists = [[1,4,5],[1,3,4],[2,6]]
         A: 1 -> 4 -> 5
         B: 1 -> 3 -> 4
         C: 2 -> 6
         Push of heads into min-heap (priority queue):
         PQ = [1(A), 1(B), 2(C)]  -> []tail                   -> Head Node: 1, Node back on queue: 4
         PQ = [1(B), 2(C), 4(A)]  -> [1]                      -> Head Node: 1, Node back on queue: 3
         PQ = [2(C), 3(B), 4(A)]  -> [1, 1]                   -> Head Node: 2, Node back on queue: 6
         PQ = [3(B), 4(A), 6(C)]  -> [1, 1, 2]                -> Head Node: 3, Node back on queue: 4
         PQ = [4(A), 4(B), 6(C)]  -> [1, 1, 2, 3]             -> Head Node: 4, Node back on queue: 5
         PQ = [4(B), 5(A), 6(C)]  -> [1, 1, 2, 3, 4]          -> Head Node: 4
         PQ = [5(A), 6(C)]        -> [1, 1, 2, 3, 4, 4]       -> Head Node: 5
         PQ = [6(C)]              -> [1, 1, 2, 3, 4, 4, 5]    -> Head Node: 6
         PQ = []                  -> [1, 1, 2, 3, 4, 4, 5, 6] -> Head Node: 6
      */


    /*
        NOTE:
        Would it not have been simpler to traverse each nodes of each of the ListNodes and insert them in order in the priority queue
        and the pass just once to linked them in order to the next node. Would the complexity have changed

        Yes : simpler to think about, but worse space, and time is effectively the same asymptotically.
     */

    private ListNode mergeKListsCommon(ListNode[] lists) {

         // Temporary sorted collection to have nodes in order
         PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            (a, b) -> Integer.compare(a.val, b.val)  // Approach 1,  returne  -1, 0, 1
                                                                       // NOTE: WE DO NOT compare the linked node's value, only the head's value
         );

         // Put the head of each non-empty list into the priority queue (Nodes not yet linked)
         for (ListNode node : lists) {
            if (node != null) minHeap.offer(node);  // Inserts the specified element into this priority queue.
                                                    // BUT do not remove current link to next node
         }

         ListNode dummy = new ListNode(0);
         ListNode tail = dummy;

         while (!minHeap.isEmpty()) {
            ListNode head = minHeap.poll();  // Retrieves and removes the head (least element) of this queue or null
            tail.next = head;                // Point current tail's next node
            tail = tail.next;                // move tail to next node

            if (head.next != null) {
                minHeap.offer(head.next);    // Inserts the last head next node, will override current tail next on next loop
            }
         }

         // Skipping temporary original head initially set to val = 0
         // return first node added
         return dummy.next;
    }

    private HardSamples_1 testMergeKLists() {
         ListNode[] kLists = new ListNode[3];
         kLists[0] = new ListNode(1, new ListNode(4, new ListNode(5)));
         kLists[1] = new ListNode(1, new ListNode(3, new ListNode(4)));
         kLists[2] = new ListNode(2, new ListNode(6));
         ListNode merged = mergeKListsCommon(kLists);

         System.out.println("Sorting using common method");
         while (merged != null) {
             System.out.print(merged.val + " ");
             merged = merged.next;
         }
         System.out.println();

        System.out.println("Sorting using alt method");
        ListNode mergedAlt = mergeKListsAlt(kLists);
        while (mergedAlt != null) {
            System.out.print(mergedAlt.val + " ");
            mergedAlt = mergedAlt.next;
        }
        System.out.println();
         return this;
    }


    // Recursive implementation
    private boolean isMatch(String s, String p) {

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

    private HardSamples_1 testExpressionMatch() {

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

    /*
          Basically the actual requirement when ignoring the O() complexity and binary search use, would be to simply
          merge and sort the 2 arrays and then if odd get the middle element else if even then average of 2 middle elements
          You would get the result but with O(m + n)

          To achieve O(log(m+n)), you need a logarithmic-selection strategy (like partitioning/eliminating half each step),
          which is effectively binary-search style even if not written as classic low/high on one array

          To solve the "Median of Two Sorted Arrays" problem using binary search and having complexity of O(log(m + n))
          the goal is to partition both arrays into two halves (a left half and a right half) such that
          - the total number of elements in the left halves equals the total number of elements in the right halves, and
          - every element on the left side is less than or equal to every element on the right side.

          ===========================================================================================================

          Imagine merging the two arrays into one large sorted array and cutting it exactly in half.
          Left Half: Contains a mix of some elements from nums1 and some from nums2.
          Right Half: Contains the remaining elements from both arrays.
          If we make a partition in nums1 at index i,
          the partition in nums2 at index j is automatically determined because
          the left half must always hold exactly half of the total elements:

          where      m + n + 1
               j =  ----------  - i
                        2

          A partition is valid if the following conditions are met:
          1- nums1[i-1] <= nums2[j] (The largest element in nums1's left side is \(\le \) the smallest element in nums2's right side).
          2- nums2[j-1] <= nums1[i] (The largest element in nums2's left side is \(\le \) the smallest element in nums1's right side)

          ===========================================================================================================

          Algorithm
          1- Ensure nums1 is the shorter array: If (m > n), swap nums1 and nums2. This guarantees the binary search takes (O(log(min(m, n)))) time.
          2- Initialize Pointers: Set low = 0 and high = m (the length of the shorter (LHS) array).
          3- Binary Search Loop: While low <= high:
             3.1 - Calculate partition index i for nums1 as (low + high) // 2
             3.2 - Calculate partition index j for nums2 as (m + n + 1)  // 2 - i
             3.3 - Identify boundary elements: L1 (nums1[i-1]), R1 (nums1[i]), L2 (nums2[j-1]), and R2 (nums2[j]).
                   Note: If a partition falls out of bounds (0 or max length), use (-infty) for left variables and (+infty) for right variables.
             3.4   Check Validity:
                   3.4.1 If L1 <= R2 and L2 <= R1, you found the correct partition!
                         - If the total number of elements (\(m+n\)) is odd, the median is max(L1, L2).
                         - If even, the median is (max(L1, L2) + min(R1, R2)) / 2.0.
                   3.4.2 If L1 > R2, it means you took too many elements from nums1.
                         Move the partition left by setting high = i - 1.
                   3.4.3 If L2 > R1, you took too few elements from nums1.
                         Move the partition right by setting low = i + 1.
     */
    private double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 1- Ensure nums1 is the smaller array for minimal binary search range, to guaranty O(log(m + n))
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        // 2- Initialize pointers
        int m = nums1.length, n = nums2.length;
        int low = 0, high = m;

        // Binary search loop
        // This is to avoid the O(m + n) complexity you would get by first merging and sorting the 2 array to get the middle
        while (low <= high) {
            int i = low + (high - low) / 2;     // Calculate partition index i for nums1 as (low + high)
            int j = (m + n + 1) / 2 - i;        // Calculate partition index j for nums2 as (m + n + 1) // 2 - i.

            // Note: If a partition falls out of bounds (0 or max length), use Integer.MIN_VALUE for left variables and Integer.MAX_VALUE for right variables.
            int left1  = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int right1 = (i == m) ? Integer.MAX_VALUE : nums1[i];

            // Note: If a partition falls out of bounds (0 or max length), use Integer.MIN_VALUE for left variables and Integer.MAX_VALUE for right variables.
            int left2  = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int right2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // Correct partition found
            if (left1 <= right2 && left2 <= right1) {
                if (((m + n) & 1) == 1) {
                    return Math.max(left1, left2); // odd length
                } else {
                    return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0; // even
                }
            }
            // Move partition in nums1 left
            else if (left1 > right2) {
                high = i - 1;
            }
            // Move partition in nums1 right
            else {
                low = i + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted properly.");
    }

    private HardSamples_1 testFindMedianSortedArrays() {
        /*
        Example 1:

        Input: nums1 = [1,3], nums2 = [2]
        Output: 2.00000
        Explanation: merged array = [1,2,3] and median is 2.

        Example 2:

        Input: nums1 = [1,2], nums2 = [3,4]
        Output: 2.50000
        Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.



        Example 3:

        Input: nums1 = [1,3,8], nums2 = [7,9,10,11]
        Output: 8,00
        Explanation: merged array = [1,3,7,8,9,10,11] and median is 8.

            - Run 1 : partitions
              nums1 split at i=1: left [1], right [3,8]
              nums2 split at j=3: left [7,9,10], right [11]

            - Run 2 : partitions
              nums1: left [1,3], right [8]
              nums2: left [7,9], right [10,11]

            - Run 3 : partitions
              nums1: left [1,3,8], right []
              nums2: left [7], right [9,10,11]

            - Median = max(left1, left2) = max(8, 7) = 8

        Example 4:

        Input: nums1 = [1,3,7,8], nums2 = [9,10,11]
        Output: 8,00
        Explanation: merged array = [1,3,7,8,9,10,11] and median is 8.
        */

        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(String.format("findMedianSortedArrays: [1,3] & [2] %f", findMedianSortedArrays(nums1, nums2)));

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println(String.format("findMedianSortedArrays: [1,2] & [3,4]: %f", findMedianSortedArrays(nums3, nums4)));

        int[] nums5 = {1, 3, 8};
        int[] nums6 = {7, 9, 10, 11};
        System.out.println(String.format("findMedianSortedArrays: [1, 3, 8] & [7, 9, 10, 11]: %f", findMedianSortedArrays(nums5, nums6)));

        int[] nums7 = {1, 3, 7, 8};
        int[] nums8 = {9, 10, 11};
        System.out.println(String.format("findMedianSortedArrays: [1, 3, 7, 8] & [9, 10, 11]: %f", findMedianSortedArrays(nums7, nums8)));
        return this;
    }

    @Override
    public void demo() {
        new HardSamples_1()
            .testExpressionMatch()
            .testFindMedianSortedArrays()
            .testMergeKLists()
        ;
    }
}
