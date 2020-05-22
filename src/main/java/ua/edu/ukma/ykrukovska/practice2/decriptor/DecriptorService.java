package ua.edu.ukma.ykrukovska.practice2.decriptor;

import ua.edu.ukma.ykrukovska.practice2.message.Message;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.IntStream;

public class DecriptorService {

    private static final int DECRIPTOR_AMOUNT = 5;
    private List<Thread> decriptors = new LinkedList<>();
    private BlockingDeque<Message> messageBucket = new LinkedBlockingDeque<>();


    public DecriptorService() {

        for (int i = 0; i < DECRIPTOR_AMOUNT; i++) {
            decriptors.add(new Thread(new DecriptorImpl(messageBucket)));
        }

        IntStream.range(0, DECRIPTOR_AMOUNT).forEach(i -> decriptors.get(i).start());

    }

    public void putForDecription(Message message){
        messageBucket.add(message);
    }


}
