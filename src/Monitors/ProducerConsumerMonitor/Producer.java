package Monitors.ProducerConsumerMonitor;
public class Producer implements Runnable {

    public void run() {
        while (n != 0) {
            Item item = new Item();
            try {
                buffer.put(item);
            } catch (InterruptedException e) {
                System.out.println("Producer interrupted!");
            }
            System.out.println("Producer #" + id + ": " + item.value());
            if (n > 0)
                n = n - 1;
        }
    }

    // continuously produce items
    // and put them in `buffer'
    Producer(Buffer<Item> buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    // produce `n' items
    // and put them in `buffer'
    Producer(Buffer<Item> buffer, int id, int n) {
        this(buffer, id);
        this.n = n;
    }

    private final Buffer<Item> buffer;
    private final int id;
    private int n = -1;
}
