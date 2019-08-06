package Philosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BaseTable implements Table {

    Lock[] forks;

    public int left(int k) {
        return k;
    }

    public int right(int k) {
        return (k + 1) % N;
    }

    public void getForks(int k) {
        // pick up left fork
        forks[left(k)].lock();
        // pick up right fork
        forks[right(k)].lock();
    }

    public void putForks(int k) {
        // put down left fork
        forks[left(k)].unlock();
        // put down right fork
        forks[right(k)].unlock();
    }

    private final int N;

    // initialize a table of N philosophers
    BaseTable(int N) {
        this.N = N;
        forks = new ReentrantLock[N];
        for (int i = 0; i < forks.length; i++)
            forks[i] = new ReentrantLock();
    }
}
