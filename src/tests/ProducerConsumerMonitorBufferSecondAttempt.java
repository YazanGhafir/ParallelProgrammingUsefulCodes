package tests;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 An implementation of producer-consumer with a bounded buffer monitor
 uses two condition variables.
 */
public class ProducerConsumerMonitorBufferSecondAttempt<T> implements PCBuffer<T> {

    private int capacity;
    private List<T> storage = new LinkedList<T>();

    public ProducerConsumerMonitorBufferSecondAttempt(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public synchronized void put(T item) {
        try {
            while (storage.size() == capacity)
                wait();
            storage.add(item);
            notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized T get() throws InterruptedException {
        while (storage.size() == 0)
            wait();
        T item = storage.remove(storage.size() - 1);
        notify();
        return item;
    }

    @Override
    public synchronized int count() {
        return storage.size();
    }


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public synchronized String toString() {
        return "ProducerConsumerMonitorBuffer{" +
                "storage=" + storage.toString() +
                '}';
    }

}
