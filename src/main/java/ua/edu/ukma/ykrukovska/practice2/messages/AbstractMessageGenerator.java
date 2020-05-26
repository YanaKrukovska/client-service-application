package ua.edu.ukma.ykrukovska.practice2.messages;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;

public abstract class AbstractMessageGenerator<T> {
    protected final Channel<T> outboundChannel;
    private final static int DEFAULT_MESSAGE_AMOUNT = 20;
    protected final int messageCount;
    protected final boolean infiniteGeneration;
    protected boolean stopGeneration = false;

    private AbstractMessageGenerator(Channel<T> outboundChannel, int messageCount, boolean infiniteGeneration) {
        this.outboundChannel = outboundChannel;
        this.messageCount = messageCount;
        this.infiniteGeneration = infiniteGeneration;
    }

    public AbstractMessageGenerator(Channel<T> outboundChannel) {
        this(outboundChannel, DEFAULT_MESSAGE_AMOUNT, false);
    }

    public AbstractMessageGenerator(Channel<T> outboundChannel, int messageCount) {
        this(outboundChannel, messageCount, false);
    }

    public void stopGeneration(){
        stopGeneration = true;
    }
    public abstract void generateMessages() throws InterruptedException;

}
