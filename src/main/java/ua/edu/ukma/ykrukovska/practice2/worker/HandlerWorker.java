package ua.edu.ukma.ykrukovska.practice2.worker;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;
import ua.edu.ukma.ykrukovska.practice2.messages.InsecureMessage;
import ua.edu.ukma.ykrukovska.practice2.domain.DomainObject;

public class HandlerWorker extends Worker<DomainObject, InsecureMessage> {
    private static int workerCounter = 0;

    public HandlerWorker(Channel<DomainObject> inputChannel, Channel<InsecureMessage> outputChannel) {
        super(inputChannel, outputChannel);
        id = this.getClass().getSimpleName() + ":" + ++workerCounter;
    }

    @Override
    protected InsecureMessage process(DomainObject inputData) {
        return new InsecureMessage(0, 0, "ok: ");
    }
}
