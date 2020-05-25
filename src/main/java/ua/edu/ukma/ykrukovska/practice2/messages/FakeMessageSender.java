package ua.edu.ukma.ykrukovska.practice2.messages;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;
import ua.edu.ukma.ykrukovska.practice2.processor.AbstractDataProcessingService;
import ua.edu.ukma.ykrukovska.practice2.worker.SenderWorker;

public class FakeMessageSender extends AbstractDataProcessingService<Message, Message> {

    public FakeMessageSender(Channel<Message> inputChannel, Channel<Message> outputChannel) {
        super(inputChannel, outputChannel);
    }

    @Override
    protected void initWorkers() {
            workers.add(new Thread(new SenderWorker(inputChannel, outputChannel)));
    }
}
