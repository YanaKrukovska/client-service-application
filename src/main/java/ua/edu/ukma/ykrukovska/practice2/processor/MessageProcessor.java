package ua.edu.ukma.ykrukovska.practice2.processor;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;
import ua.edu.ukma.ykrukovska.practice2.domain.DomainObject;
import ua.edu.ukma.ykrukovska.practice2.messages.InsecureMessage;
import ua.edu.ukma.ykrukovska.practice2.worker.DomainWorker;

public class MessageProcessor extends AbstractDataProcessingService<InsecureMessage, DomainObject> {

    public MessageProcessor(Channel<InsecureMessage> inputChannel, Channel<DomainObject> outputChannel) {
        super(inputChannel, outputChannel);
    }

    @Override
    protected void initWorkers() {
        for (int i = 0; i < workerAmount; i++) {
            Thread thread = new Thread(new DomainWorker(inputChannel, outputChannel));
            thread.setDaemon(false);
            workers.add(thread);
        }
    }
}
