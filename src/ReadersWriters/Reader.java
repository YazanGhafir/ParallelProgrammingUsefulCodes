package ReadersWriters;
public class Reader implements Runnable {

    public void run() {
        while (n != 0) {
            Message msg = null;
            try {
                // read message from board
                msg = board.read();
                // do something with `msg'
                System.out.println("Reader #" + id + ": " + msg.text());
            } catch (InterruptedException e) {
                System.out.println("Reader interrupted!");
            }
            if (n > 0) n = n - 1;
        }
    }

    // continuously read items from `board'
    Reader(Board<Message> board, int id) {
        this.board = board;
        this.id = id;
    }

    // read `n' items from `board'
    Reader(Board<Message> board, int id, int n) {
        this(board, id);
        this.n = n;
    }

    private final Board<Message> board;
    private final int id;
    private int n = -1;
}
