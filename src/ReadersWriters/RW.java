package ReadersWriters;
public class RW {

    private static void print_usage() {
        System.out.println("Usage: java RW #writers #writes #readers #reads");
        System.out.println("  use -1 for unbounded #writes/reads");
        System.exit(0);
    }

    public static void main(String[] args) {
        if (args.length != 4) {
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

        int nWriters = intArgs[0];
        int nWrites = intArgs[1];
        int nReaders = intArgs[2];
        int nReads = intArgs[3];
        Board<Message> board = new FairBoard<>();
        //Board<Message> board = new SyncBoard<>();
        try {
            // write initial message to board
            Message msg = new Message();
            board.write(msg);
            System.out.println("Main thread: " + msg.text());
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted!");
            System.exit(0);
        }
        Thread[] threads = new Thread[nWriters + nReaders];
        for (int i = 0; i < nWriters; i++) {
            Writer p = new Writer(board, i, nWrites);
            Thread t = new Thread(p);
            threads[i] = t;
        }
        for (int i = 0; i < nReaders; i++) {
            Reader c = new Reader(board, i, nReads);
            Thread t = new Thread(c);
            threads[nWriters + i] = t;
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
