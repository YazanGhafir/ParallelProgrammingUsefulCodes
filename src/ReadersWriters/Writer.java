package ReadersWriters;
public class Writer implements Runnable {

    public void run() {
        while (n != 0) {
            // create a new message
            Message msg = new Message();
            try {
                // write `msg' to board
                board.write(msg);
                System.out.println("Writer #" + id + ": " + msg.text());
            } catch (InterruptedException e) {
                System.out.println("Writer interrupted!");
            }
            if (n > 0) n = n - 1;
        }
    }

    // continuously produce messages and put them on `board'
    Writer(Board<Message> board, int id) {
        this.board = board;
        this.id = id;
    }

    // produce `n' messages and put them on `board'
    Writer(Board<Message> board, int id, int n) {
        this(board, id);
        this.n = n;
    }

    private final Board<Message> board;
    private final int id;
    private int n = -1;
}
