package tests;

import java.nio.Buffer;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.AbstractQueuedSynchronizer.ConditionObject;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 An implementation of producer-consumer with a bounded buffer monitor
 uses two condition variables.
 */
public class ProducerConsumerMonitorBuffer<T> implements PCBuffer<T> {

    private int capacity;
    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();
    private List<T> storage = new LinkedList<T>();

    public ProducerConsumerMonitorBuffer(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void put(T item) {
        if (storage.size() == capacity) {
            try {
                notFull.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.add(item);
        notEmpty.signal();
    }

    @Override
    public T get() {
        if (storage.size() == 0) {
            try {
                notEmpty.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T item = storage.remove(storage.size() - 1);
        notFull.signal();

        return item;
    }

    @Override
    public int count() {
        return storage.size();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "ProducerConsumerMonitorBuffer{" +
                "storage=" + storage.toString() +
                '}';
    }

}
