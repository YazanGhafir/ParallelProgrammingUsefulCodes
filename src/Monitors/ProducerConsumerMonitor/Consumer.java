package Monitors.ProducerConsumerMonitor;

public class Consumer implements Runnable {

    public void run() {
        Item item;
        while (n != 0) {
            item = null;
            try {
                item = buffer.get();
            } catch (InterruptedException e) {
                System.out.println("Consumer interrupted!");
            }
            System.out.println("Consumer #" + id + ": " + item.value());
            if (n > 0)
                n = n - 1;
        }
    }

    // continuously consume items from `buffer'
    Consumer(Buffer<Item> buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    // consume `n' items from `buffer'
    Consumer(Buffer<Item> buffer, int id, int n) {
        this(buffer, id);
        this.n = n;
    }

    private final Buffer<Item> buffer;
    private final int id;
    private int n = -1;
}
