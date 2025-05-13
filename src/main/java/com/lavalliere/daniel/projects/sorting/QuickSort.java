package com.lavalliere.daniel.projects.sorting;


import com.lavalliere.daniel.projects.annotations.Demoable;
import com.lavalliere.daniel.projects.annotations.IsDemoable;

import java.util.Arrays;

@IsDemoable
public class QuickSort implements Demoable {


    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];   // The algorithm allows to select your preferred pivot location
                                 // In this case we always select the RHS element

        int i = (low -1); // Current LHS partition index pos to determine the LHS parition size

        // Locate all elements lower or equal and swap them, to LHS partition
        for (int j=low; j < high; j++) {
            if (arr[j] <= pivot) {
                
                i++; // Swapping an element

                // Swap the 2 elements
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap the pivot element to the LHS partition
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // Return the first element of the RHS partition
        return i + 1;
    }

    private void printArray(int[] arr) {
        System.out.println("The sorted array: ");
        Arrays.stream(arr).forEach(e -> System.out.printf("%d ", e));
        System.out.println();
    }

    void sort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotElementIndex = partition(arr, low, high);

            sort(arr, low, pivotElementIndex-1);
            sort(arr, pivotElementIndex+1, high);
        }
    }

    public QuickSort testSorting() {
        int arr[] = {10, 7, 8, 9, 1, 5, 11};
        sort(arr,0,arr.length-1);
        printArray(arr);
        System.out.println();
        return this;
    }

    public void demo() {
        new QuickSort()
            .testSorting()
        ;
    }
}
