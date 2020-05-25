package ua.edu.ukma.ykrukovska.practice2.worker;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;
import ua.edu.ukma.ykrukovska.practice2.messages.InsecureMessage;
import ua.edu.ukma.ykrukovska.practice2.messages.Message;

public class EncriptorWorker extends Worker<InsecureMessage, Message> {
    private static int workerCounter = 0;

    public EncriptorWorker(Channel<InsecureMessage> inputChannel, Channel<Message> outputChannel) {
        super(inputChannel, outputChannel);
        id = this.getClass().getSimpleName() + ":" + ++workerCounter;
    }

    @Override
    protected Message process(InsecureMessage inputData) {
        return new Message(new Message.MessageBuilder().type(0).userId(0).message(inputData.getMessage()));
    }


}
