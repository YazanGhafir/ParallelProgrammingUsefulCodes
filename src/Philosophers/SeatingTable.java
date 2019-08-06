package Philosophers;
import java.util.concurrent.Semaphore;

public class SeatingTable extends BaseTable {

    @Override
    public void getForks(int k) {
        try {
            seats.acquire();
        } catch (InterruptedException e) {
            System.out.println("Interrupted on get(" + k + ")...");
        }
        super.getForks(k);
    }

    @Override
    public void putForks(int k) {
        super.putForks(k);
        seats.release();
    }

    private final Semaphore seats;

    // initialize a table of N philosophers
    // with at most M < N active at the table
    SeatingTable(int N, int M) {
        super(N);
        // fair semaphore, with capacity M
        seats = new Semaphore(M, true);
    }
}
