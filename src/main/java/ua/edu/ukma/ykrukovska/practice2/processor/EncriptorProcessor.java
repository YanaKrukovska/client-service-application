package ua.edu.ukma.ykrukovska.practice2.processor;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;
import ua.edu.ukma.ykrukovska.practice2.messages.InsecureMessage;
import ua.edu.ukma.ykrukovska.practice2.messages.Message;
import ua.edu.ukma.ykrukovska.practice2.worker.EncriptorWorker;

public class EncriptorProcessor extends AbstractDataProcessingService<InsecureMessage, Message> {
    public EncriptorProcessor(Channel<InsecureMessage> inputChannel, Channel<Message> outputChannel) {
        super(inputChannel, outputChannel);
    }

    @Override
    protected void initWorkers() {
        for (int i = 0; i < workerAmount; i++) {
            Thread thread = new Thread(new EncriptorWorker(inputChannel, outputChannel));
            thread.setDaemon(false);
            workers.add(thread);
        }
    }
}
