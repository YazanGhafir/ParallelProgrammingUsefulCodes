package Philosophers;
import java.util.Random;

public class Philosopher implements Runnable {

    public void think() {
        System.out.println("Philosopher " + id + " is thinking about stuff.");
        waitSome();
    }

    public void eat() {
        System.out.println("Philosopher " + id + " is eating nice food!");
        waitSome();
    }

    public void run() {
        while (true) {
            think();
            table.getForks(id);
            eat();
            table.putForks(id);
        }
    }

    private final Table table;
    private final int id;
    Philosopher(int id, Table table) {
        this.id = id;
        this.table = table;
    }

    private final Random rng = new Random();

    // stay idle for a random amount of time
    private void waitSome() {
        try {
            Thread.sleep(rng.nextInt(1000));
        } catch (InterruptedException e) {
            System.out.println ("Philsopher " + id + " interrupted!");
        }
    }
}

