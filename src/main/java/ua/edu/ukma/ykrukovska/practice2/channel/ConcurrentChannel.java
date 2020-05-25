package ua.edu.ukma.ykrukovska.practice2.channel;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ConcurrentChannel<T> implements Channel<T> {

    private final BlockingDeque<T> messages = new LinkedBlockingDeque();

    @Override
    public void submit(T message) throws InterruptedException {
        messages.put(message);
    }

    @Override
    public T take() throws InterruptedException {
        return messages.take();
    }
}
