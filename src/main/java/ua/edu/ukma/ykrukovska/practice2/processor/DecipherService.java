package ua.edu.ukma.ykrukovska.practice2.processor;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;
import ua.edu.ukma.ykrukovska.practice2.messages.Message;
import ua.edu.ukma.ykrukovska.practice2.messages.InsecureMessage;
import ua.edu.ukma.ykrukovska.practice2.worker.DecipherWorker;

public class DecipherService extends AbstractDataProcessingService<Message, InsecureMessage> {

    public DecipherService(Channel<Message> inputChannel, Channel<InsecureMessage> outputChannel) {
        super(inputChannel, outputChannel);
    }

    @Override
    protected void initWorkers() {
        for (int i = 0; i < workerAmount; i++) {
            workers.add(new Thread(new DecipherWorker(inputChannel, outputChannel)));
        }
    }
}
