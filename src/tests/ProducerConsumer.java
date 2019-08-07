package tests;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumer {

    public static void main(String[] args) {

        /***********************  My implementation by ProducerConsumer by BlockingQueue ************************/

        BlockingQueue blockingQueue = new LinkedBlockingQueue<String>(10);


        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                try {
                    while (true) {
                        blockingQueue.put("s" + i++);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                try {
                    while (true) {
                        blockingQueue.put("s" + i--);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        blockingQueue.take();
                        if (blockingQueue.size() > 5)
                            System.out.println(blockingQueue.toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
