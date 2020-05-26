package ua.edu.ukma.ykrukovska.practice2.channel;

public interface Channel<T> {
    void submit(T message) throws InterruptedException;

    T take() throws InterruptedException;

    int size();

}
