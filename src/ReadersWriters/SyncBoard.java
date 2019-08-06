package ReadersWriters;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SyncBoard<T> implements Board<T> {

  int nReaders = 0;                   // # readers on board
  Lock lock = new ReentrantLock();    // for exclusive access to nReaders
  Semaphore empty = new Semaphore(1); // 1 iff no active threads
  T message;                          // current message

  public T read() throws InterruptedException {
    lock.lock();                     // lock to update nReaders
    if (nReaders == 0) empty.acquire(); // if first reader, set not empty
    nReaders += 1;                   // update active readers
    lock.unlock();                   // release lock to nReaders
    T msg = message;                 // read (critical section)
    lock.lock();                     // lock to update nReaders
    nReaders -= 1;                   // update active readers
    if (nReaders == 0) empty.release();   // if last reader, set empty
    lock.unlock();                   // release lock to nReaders
    return msg;
  }

  public void write(T msg) throws InterruptedException {
    // get exclusive access
    empty.acquire();
    message = msg; // write (cs)
    // release board
    empty.release();
  }
}
