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
    private Lock monitor = new ReentrantLock(true);
    private Condition notEmpty = monitor.newCondition();
    private Condition notFull = monitor.newCondition();
    private List<T> storage = new LinkedList<T>();

    public ProducerConsumerMonitorBuffer(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void put(T item) {
        monitor.lock();
        try {
            while (storage.size() == capacity)
                notFull.await();
            storage.add(item);
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            monitor.unlock();
        }
    }

    @Override
    public T get() throws InterruptedException {
        monitor.lock();
        try {
            while (storage.size() == 0)
                notEmpty.await();
            T item = storage.remove(storage.size()-1);
            notFull.signal();
            return item;
        } finally {
            monitor.unlock();
        }
    }
        @Override
        public int count() {
            monitor.lock();
            try {
                return storage.size();
            } finally {
                monitor.unlock();
            }
        }


    public int getCapacity () {
            return capacity;
        }

        public void setCapacity ( int capacity){
            this.capacity = capacity;
        }

        @Override
        public String toString () {
            return "ProducerConsumerMonitorBuffer{" +
                    "storage=" + storage.toString() +
                    '}';
        }

    }
