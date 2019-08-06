package ProducerConsumer;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Queue;
import java.util.LinkedList;

public class SBuffer<T> implements Buffer<T> {

    private final Lock lock = new ReentrantLock();
    private final Semaphore nItems = new Semaphore(0);
    private final Semaphore nFree;
    private final Queue<T> storage = new LinkedList<>();

    // invariant: storage.size() == nItems.availablePermits()

    // add item to buffer; block if full
    public void put(T item) throws InterruptedException {
        if (nFree != null)
            nFree.acquire();
        lock.lock();
        try {
            storage.add(item);
            nItems.release();
        } finally {
            lock.unlock();
        }
    }

    // remove item from buffer; block if empty
    public T get() throws InterruptedException {
        nItems.acquire();
        lock.lock();
        T item;
        try {
            item = storage.remove();
        } finally {
            lock.unlock();
        }
        if (nFree != null)
            nFree.release();
        return item;
    }

    // number of items in buffer
    public int count() {
        return nItems.availablePermits();
    }

    SBuffer(int maxSize) {
        if (maxSize > 0)
            nFree = new Semaphore(maxSize);
        else
            nFree = null;
    }
}
