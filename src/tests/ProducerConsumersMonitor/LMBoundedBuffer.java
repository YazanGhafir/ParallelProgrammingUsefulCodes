package tests.ProducerConsumersMonitor;
import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class LMBoundedBuffer<T> implements Buffer<T> {
    private final int capacity;
    private final Queue<T> storage = new LinkedList<>();
    private final Lock monitor = new ReentrantLock(true);
    private final Condition notFull = monitor.newCondition();
    private final Condition notEmpty = monitor.newCondition();

    // add item to buffer; block if full
    public void put(T item)
    throws InterruptedException {
        monitor.lock();
        try {
            while (storage.size() == capacity)
                notFull.await();
            storage.add(item);
            notEmpty.signal();
        } finally {
            monitor.unlock();
        }
    }

    // remove item from buffer; block if empty
    public T get()
    throws InterruptedException {
        monitor.lock();
        try {
            while (storage.size() == 0)
                notEmpty.await();
            T item = storage.remove();
            notFull.signal();
            return item;
        } finally {
            monitor.unlock();
        }
    }

    // number of items in buffer
    public int count() {
        monitor.lock();
        try {
            return storage.size();
        } finally {
            monitor.unlock();
        }
    }

    LMBoundedBuffer(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Bounded buffer requires capacity > 0");
        this.capacity = capacity;
    }
}


