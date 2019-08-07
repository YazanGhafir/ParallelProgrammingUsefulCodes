package tests.ProducerConsumersMonitor;

interface Buffer<T> {
    // add item to buffer; block if full
    void put(T item) throws InterruptedException;

    // remove item from buffer; block if empty
    T get() throws InterruptedException;

    // number of items in buffer
    int count();
}
