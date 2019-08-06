package ReadersWriters;
interface Board<T> {
    // write message `msg' to board
    void write(T msg) throws InterruptedException;
    // read current message on board
    T read() throws InterruptedException;
}
