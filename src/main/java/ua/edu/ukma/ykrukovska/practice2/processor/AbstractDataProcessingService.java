package ua.edu.ukma.ykrukovska.practice2.processor;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractDataProcessingService<I, O> {
    protected Channel<I> inputChannel;
    protected Channel<O> outputChannel;
    protected List<Thread> workers;
    protected final static int workerAmount = 5;


    public AbstractDataProcessingService(Channel<I> inputChannel, Channel<O> outputChannel) {
        this.inputChannel = inputChannel;
        this.outputChannel = outputChannel;
        workers = new LinkedList<>();
        initWorkers();
    }

    protected abstract void initWorkers();

    public void startProcessing() {
        workers.forEach(Thread::start);
    }

    public void stopProcessing() {
        workers.forEach(Thread::interrupt);
    }
}

