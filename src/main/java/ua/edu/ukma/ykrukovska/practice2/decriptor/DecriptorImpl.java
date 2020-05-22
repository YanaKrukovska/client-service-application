package ua.edu.ukma.ykrukovska.practice2.decriptor;

import ua.edu.ukma.ykrukovska.practice2.message.Message;

import java.util.concurrent.BlockingDeque;

public class DecriptorImpl implements Decriptor, Runnable {

    private BlockingDeque<Message> messageBucket;
    private static int decriptorCounter = 0;
    private int decriptorId;


    public DecriptorImpl(BlockingDeque<Message> messageBucket) {
        this.messageBucket = messageBucket;
        this.decriptorId = decriptorCounter++;
    }

    @Override
    public void decript(Message message) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("DECRIPTOR " + decriptorId + ", processing " + message.toString());
    }

    @Override
    public void run() {
        while (true) {
            try {
                decript(messageBucket.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
