package Philosophers;
interface Table {
    // philosopher k picks up forks
    void getForks(int k);

    // philosopher k releases forks
    void putForks(int k);
}
