package ua.edu.ukma.ykrukovska.practice2.worker;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;

public abstract class Worker<I, O> implements Runnable {
    private Channel<I> inboundChannel;
    private Channel<O> outboundChannel;
    protected String id;

    private final static int SLEEP_TIME = 1000;

    public Worker(Channel<I> inboundChannel, Channel<O> outboundChannel) {
        this.inboundChannel = inboundChannel;
        this.outboundChannel = outboundChannel;
    }

    @Override
    public void run() {

         while (true) {
            try {
                I itemToProcess = inboundChannel.take();
                O processedItem = process(itemToProcess);
                if (outboundChannel != null) {
                    outboundChannel.submit(processedItem);
                }
                System.out.println(id + " " + itemToProcess.toString() + " ====> " + processedItem);
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    protected abstract O process(I inputData);

}



