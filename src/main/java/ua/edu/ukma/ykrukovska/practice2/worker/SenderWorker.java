package ua.edu.ukma.ykrukovska.practice2.worker;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;
import ua.edu.ukma.ykrukovska.practice2.messages.Message;

public class SenderWorker extends Worker<Message, Message> {
    private static int workerCounter = 0;

    public SenderWorker(Channel<Message> inboundChannel, Channel<Message> outboundChannel) {
        super(inboundChannel, outboundChannel);
        id = this.getClass().getSimpleName() + ":" + ++workerCounter;
    }

    @Override
    protected Message process(Message inputData) {
        return inputData;
    }
}
