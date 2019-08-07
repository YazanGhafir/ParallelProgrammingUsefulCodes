package tests.ProducerConsumersMonitor;
import java.util.Random;

public class Item {

    private final int value;

    // create a random item
    Item() {
        value = rng.nextInt();
    }

    public int value() {
        return value;
    }

    private static final Random rng = new Random();
}
