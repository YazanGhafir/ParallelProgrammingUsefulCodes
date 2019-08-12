package Sorting;

import sun.awt.windows.ThemeReader;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

public class ParallelDummyHeapSort<T extends Comparable>{

    public static <T extends Comparable> void sort(T[] array){

        Queue<T> pq = new SynchronizedPriorityQueue<T>();

        int a = array.length/10;

        Thread sorter1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < a; j++){
                    pq.offer(array[j]);
                }
            }
        });

        Thread sorter2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = a+1; j < 2*a; j++){
                    pq.offer(array[j]);
                }
            }
        });

        Thread sorter3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 2*a+1; j < 3*a; j++) {
                    pq.offer(array[j]);
                }
            }
        });

        Thread sorter4 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 3*a+1; j < 4*a; j++){
                    pq.offer(array[j]);
                }
            }
        });

        Thread sorter5 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 4*a+1; j < 5*a; j++){
                    pq.offer(array[j]);
                }
            }
        });

        Thread sorter6 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 5*a+1; j < 6*a; j++){
                    pq.offer(array[j]);
                }
            }
        });
        Thread sorter7 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 6*a + 1; j < 7*a; j++){
                    pq.offer( array[j]);
                }
            }
        });

        Thread sorter8 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 7*a + 1; j < 8*a; j++){
                    pq.offer( array[j]);
                }
            }
        });

        Thread sorter9 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 8*a+1; j < 9*a; j++){
                    pq.offer( array[j]);
                }
            }
        });

        Thread sorter10 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 9*a+1; j < array.length; j++){
                    pq.offer( array[j]);
                }
            }
        });

        sorter1.start();
        sorter2.start();
        sorter3.start();
        sorter4.start();
        sorter5.start();
        sorter6.start();
        sorter7.start();
        sorter8.start();
        sorter9.start();
        sorter10.start();

        try {
            sorter1.join();
            sorter2.join();
            sorter3.join();
            sorter4.join();
            sorter5.join();
            sorter6.join();
            sorter7.join();
            sorter8.join();
            sorter9.join();
            sorter10.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < pq.size(); i++){
            array[i] = (T) pq.remove();
        }

    }

    public static class SynchronizedPriorityQueue <T extends Comparable> extends PriorityQueue{
        @Override
        public synchronized boolean offer(Object o) {
            return super.offer(o);
        }
    }
}
