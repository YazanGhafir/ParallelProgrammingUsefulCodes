package Sorting;

import java.util.PriorityQueue;
import java.util.Queue;

public class DummyHeapSort {

    public static <T extends Comparable> void sort(T[] array) {

        Queue<T> pq = new PriorityQueue<T>();

        for (int j = 0; j < array.length; j++) {
            pq.add(array[j]);
        }

        for (int i = 0; i < array.length; i++){
            array[i] = (T) pq.remove();
        }

    }
}
