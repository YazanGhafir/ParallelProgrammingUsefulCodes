package tests;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducersConsumersBlockingQueue {


    public static void main(String[] args) {


        BlockingQueue blockingQueue = new LinkedBlockingQueue<String>(1000);

        class Producer extends Thread {
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
        }

        class Consumer extends Thread {
            @Override
            public void run() {
                while (true) {
                    try {
                        blockingQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        Thread[] producers = new Thread[100];
        for (int i = 0; i < 100; i++) {
            producers[i] = new Producer();
        }


        Thread[] consumers = new Thread[100];
        for (int i = 0; i < 100; i++) {
            consumers[i] = new Consumer();
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Thread producer : producers)
                    producer.start();
                for (Thread consumer : consumers)
                    consumer.start();
                while (true) {
                    if (blockingQueue.size() > 997)
                        System.out.println(blockingQueue.toString());
                }
            }
        }).start();
    }
}
