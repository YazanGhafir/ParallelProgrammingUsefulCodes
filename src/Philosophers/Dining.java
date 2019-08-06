package Philosophers;
public class Dining {

    public static void main(String[] args) {
        int N = 5;
        if (args.length == 1) {
            try {
                N = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Ignoring invalid argument: " + args[0]);
            }
        }

        Table table = new AsymmetricTable(N);
        Thread[] threads = new Thread[N];
        for (int i = 0; i < threads.length; i++) {
            Philosopher p = new Philosopher(i, table);
            Thread t = new Thread(p);
            threads[i] = t;
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        try {
            for (int i = 0; i < threads.length; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted!");
        }
    }
}

