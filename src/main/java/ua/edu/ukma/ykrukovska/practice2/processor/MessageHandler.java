package ua.edu.ukma.ykrukovska.practice2.processor;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;
import ua.edu.ukma.ykrukovska.practice2.domain.DomainObject;
import ua.edu.ukma.ykrukovska.practice2.messages.InsecureMessage;
import ua.edu.ukma.ykrukovska.practice2.worker.HandlerWorker;

public class MessageHandler extends AbstractDataProcessingService<DomainObject, InsecureMessage> {


    public MessageHandler(Channel<DomainObject> inputChannel, Channel<InsecureMessage> outputChannel) {
        super(inputChannel, outputChannel);
    }

    @Override
    protected void initWorkers() {
        for (int i = 0; i < workerAmount; i++) {
            Thread thread = new Thread(new HandlerWorker(inputChannel, outputChannel));
            thread.setDaemon(false);
            workers.add(thread);
        }
    }
}
