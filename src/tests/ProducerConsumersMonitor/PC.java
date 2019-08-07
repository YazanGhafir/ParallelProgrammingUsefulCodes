package tests.ProducerConsumersMonitor;
public class PC {

    private static void print_usage() {
        System.out.println("Usage: java PC #producers #produced #consumers #consumed bufferSize");
        System.out.println("  use -1 for unbounded #produced/consumed and bufferSize");
        System.exit(0);
    }

    public static void main(String[] args) {
        if (args.length != 5) {
            print_usage();
        }
        int[] intArgs = new int[args.length];
        for (int i = 0; i < args.length; i++)
            try {
                intArgs[i] = Integer.parseInt(args[i]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid argument #" + i + 1);
                print_usage();
            }

        int nProducers = intArgs[0];
        int nProds = intArgs[1];
        int nConsumers = intArgs[2];
        int nCons = intArgs[3];
        int size = intArgs[4];
        Buffer<Item> buffer = new LMBoundedBuffer<>(size);
        Thread[] threads = new Thread[nProducers + nConsumers];
        for (int i = 0; i < nProducers; i++) {
            Producer p = new Producer(buffer, i, nProds);
            Thread t = new Thread(p);
            threads[i] = t;
        }
        for (int i = 0; i < nConsumers; i++) {
            Consumer c = new Consumer(buffer, i, nCons);
            Thread t = new Thread(c);
            threads[nProducers + i] = t;
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
