package ReadersWriters;
import java.util.Random;

public class Message {

    private final String text;

    // create a random message
    Message() {
        int i = rng.nextInt();
        text = "msg" + i;
    }

    public String text() {
        return text;
    }

    private static final Random rng = new Random();
}
