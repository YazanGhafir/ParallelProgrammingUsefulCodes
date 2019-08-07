package tests.ProducerConsumersMonitor;
import java.util.Queue;
import java.util.LinkedList;

public class JMBoundedBuffer<T> implements Buffer<T> {
    private final int capacity;
    private final Queue<T> storage = new LinkedList<>();

    // add item to buffer; block if full
    public synchronized void put(T item)
    throws InterruptedException {
        while (storage.size() == capacity)
            wait();
        storage.add(item);
        notifyAll(); // signal waiting consumers
    }

    // remove item from buffer; block if empty
    public synchronized T get()
    throws InterruptedException {
        while (storage.size() == 0)
            wait();
        T item = storage.remove();
        notifyAll(); // signal waiting producers
        return item;
    }

    // number of items in buffer
    public synchronized int count() {
        return storage.size();
    }

    JMBoundedBuffer(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Bounded buffer requires capacity > 0");
        this.capacity = capacity;
    }
}
