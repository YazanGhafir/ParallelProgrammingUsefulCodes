package ReadersWriters;
import java.util.concurrent.Semaphore;

public class FairBoard<T> extends SyncBoard<T> {

  // held by the next thread to go
  Semaphore baton = new Semaphore(1, true); // fair binary semaphore

  public T read() throws InterruptedException {
    // wait for my turn
    baton.acquire();
    // release a waiting thread
    baton.release();
    // read() as in SyncBoard
    return super.read();
  }

  public void write(T msg) throws InterruptedException {
    // wait for my turn
    baton.acquire();
    // write() as in SyncBoard
    super.write(msg);
    // release a waiting thread
    baton.release();
  }
}
