package Philosophers;
public class AsymmetricTable extends BaseTable {

    @Override
    public void getForks(int k) {
        if (k == 0) {
            // lock right before left
            forks[right(k)].lock();
            forks[left(k)].lock();
        } else {
            // lock left before right
            super.getForks(k);
        }
    }

    // initialize a table of N philosophers
    AsymmetricTable(int N) {
        super(N);
    }
}
