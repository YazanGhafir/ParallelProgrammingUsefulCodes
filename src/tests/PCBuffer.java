package tests;

public interface PCBuffer <T> {

    //add item to the buffer, block if full
    void put(T item);

    //remove item from the buffer, block if empty
    T get();

    //number of items in buffer
    int count();

}
