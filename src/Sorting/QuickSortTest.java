package Sorting;

import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

public class QuickSortTest {
    /**
     * Driver program to test sorting methods.
     *
     * @param args Not used
     */
    public static void main(String[] args) {
        int size = Integer.parseInt(JOptionPane.showInputDialog("Enter Array size:"));
        Integer[] items = new Integer[size]; // Array to sort.
        Integer[] copy = new Integer[size]; // Copy of array.


        Random rInt = new Random(); // For random number generation

        // Fill the array and copy with random Integers.
        for (int i = 0; i < items.length; i++) {
            items[i] = rInt.nextInt();
            copy[i] = items[i];
        }
        // Sort with utility method.
        long startTime = System.currentTimeMillis();
        Arrays.sort(items);
        System.out.println("Utility sort time is "
                + (System.currentTimeMillis()
                - startTime) + "ms");
        System.out.println("Utility sort successful (true/false): "
                + verify(items));

        // Reload array items from array copy.
        for (int i = 0; i < items.length; i++) {
            items[i] = copy[i];
        }

        // Sort with QuickSort2.
        startTime = System.currentTimeMillis();
        QuickSort2.sort(items);
        System.out.println("QuickSort time is "
                + (System.currentTimeMillis()
                - startTime) + "ms");
        System.out.println("QuickSort successful (true/false): "
                + verify(items));
        //display(items); // Display part of the array.


        // Sort with ParallelQuickSort.
        startTime = System.currentTimeMillis();
        //ParallelQuickSort.sort(items);
        System.out.println("ParallelQuickSort time is "
                + (System.currentTimeMillis()
                - startTime) + "ms");
        System.out.println("ParallelQuickSort successful (true/false): "
                + verify(items));
        //display(items); // Display part of the array.


        // Reload array items from array copy.
        for (int i = 0; i < items.length; i++) {
            items[i] = copy[i];
        }

        // Sort with ParallelDummyHeapSort.
        startTime = System.currentTimeMillis();
        DummyHeapSort.sort(items);
        System.out.println("DummyHeapSort time is "
                + (System.currentTimeMillis()
                - startTime) + "ms");
        System.out.println("DummyHeapSort successful (true/false): "
                + verify(items));
        //display(items); // Display part of the array.


        // Reload array items from array copy.
        for (int i = 0; i < items.length; i++) {
            items[i] = copy[i];
        }

        // Sort with ParallelDummyHeapSort.
        startTime = System.currentTimeMillis();
        ParallelDummyHeapSort.sort(items);
        System.out.println("ParallelDummyHeapSort time is "
                + (System.currentTimeMillis()
                - startTime) + "ms");
        System.out.println("ParallelDummyHeapSort successful (true/false): "
                + verify(items));
        //display(items); // Display part of the array.

    }

    /**
     * Verifies that the elements in array test are
     * in increasing order.
     *
     * @param test The array to verify
     * @return true if the elements are in increasing order;
     * false if any 2 elements are not in increasing order
     */
    private static boolean verify(Comparable[] test) {
        boolean ok = true;
        int i = 0;
        while (ok && (i < test.length - 1)) {
            ok = test[i].compareTo(test[i + 1]) <= 0;
            i++;
        }
        return ok;
    }

    /**
    showing first 10 elements of the array
    if the array.length >= 10 other wise all.
    @param items the array
     */
    public static void display(Integer[] items) {
        System.out.print("part (or all) of the sorted array : [");
        if (items.length >= 10) {
            for (int i = 0; i < 10; i++) {
                if (i == 9) {
                    System.out.print(items[i] + " ]");
                } else {
                    System.out.print(items[i] + " , ");
                }
            }
        } else {
            for (int i = 0; i < items.length; i++) {
                if (i == items.length - 1) {
                    System.out.print(items[i] + " ]");
                } else {
                    System.out.print(items[i] + " , ");
                }
            }
        }
    }
}
