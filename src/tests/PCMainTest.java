package tests;

public class PCMainTest {

    /*********** main-method to test the monitor **********/

    public static void main(String[] args) {

        //ProducerConsumerMonitorBuffer<String> Buffer = new ProducerConsumerMonitorBuffer<String>(10);
        ProducerConsumerMonitorBufferSecondAttempt<String> Buffer = new ProducerConsumerMonitorBufferSecondAttempt<String>(20);

        class Producer extends Thread {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    Buffer.put("s" + i++);

                }
            }
        }

        class Consumer extends Thread {
            @Override
            public void run() {
                while (true) {
                    try {
                        Buffer.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        /**
         * creates a hundred producer-threads
         */
        Thread[] producers = new Thread[100];
        for (int i = 0; i < 100; i++) {
            producers[i] = new Producer();
        }


        /**
         * creates a hundred consumer-threads
         */
        Thread[] consumers = new Thread[100];
        for (int i = 0; i < 100; i++) {
            consumers[i] = new Consumer();
        }


        /**
         * runs the war of threads
         *
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (Thread producer : producers)
                    producer.start();
                for (Thread consumer : consumers)
                    consumer.start();
                while (true) {
                    if (Buffer.count() > 17)
                        System.out.println(Buffer.toString());
                }
            }
        }).start();
*/



        new Thread(new Runnable() {
            @Override
            public void run() {
                Producer producer = new Producer();
                Consumer consumer = new Consumer();
                producer.start();
                consumer.start();

                while (true) {
                    if (Buffer.count() > 17)
                    System.out.println(Buffer.toString());
                }
            }
        }).start();
    }

}
