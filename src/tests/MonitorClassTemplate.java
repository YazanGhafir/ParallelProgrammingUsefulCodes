package tests;

import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class MonitorClassTemplate {
    //private fields
    private Lock monitor = new ReentrantLock(true);
    private final Condition blocked = monitor.newCondition(); // could be private final Condition notFull = monitor.newCondition();  private final Condition notEmpty = monitor.newCondition();
    private boolean someCondition;                            // could be for ex. private final Queue<T> storage = new LinkedList<>();
                                                              // could have private final Queue<T> storage = new LinkedList<>();
    //locked methods - signal and continue discipline
    public void method1 () throws InterruptedException{
        monitor.lock();
        try{
            while(someCondition){    // could be for ex. storage.size() == capacity    or    storage.size() == 0
                blocked.await();     // could be  notFull.await();
            }
            // doing code            // could be storage.add(item);
            blocked.signal();        // could be notEmpty.signal();
        } finally {
            monitor.unlock();
        }
    }

    //locked methods - signal and continue discipline
    public void method2 () throws InterruptedException{
        monitor.lock();
        try{
            while(someCondition){    // could be for ex. storage.size() == capacity    or    storage.size() == 0
                blocked.await();     // could be  notEmpty.await();
            }
            // doing code            // could be T item = storage.remove();
            blocked.signal();        // could be notEmpty.signal();   return item;
        } finally {
            monitor.unlock();
        }
    }

    // number of items in a buffer for ex
    public void count() {
        monitor.lock();
        try {
            // could be return storage.size();
        } finally {
            monitor.unlock();
        }
    }
}
