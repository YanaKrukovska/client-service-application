package ua.edu.ukma.ykrukovska.practice2.worker;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;
import ua.edu.ukma.ykrukovska.practice2.messages.InsecureMessage;
import ua.edu.ukma.ykrukovska.practice2.domain.DomainObject;
import ua.edu.ukma.ykrukovska.practice2.domain.Operations;

import java.math.BigDecimal;

public class DomainWorker extends Worker<InsecureMessage, DomainObject> {
    private static int workerCounter = 0;

    public DomainWorker(Channel<InsecureMessage> inputChannel, Channel<DomainObject> outputChannel) {
        super(inputChannel, outputChannel);
        id = this.getClass().getSimpleName() + ":" + ++workerCounter;
    }


    @Override
    protected DomainObject process(InsecureMessage inputData) {

        String[] domainParts = inputData.getMessage().split(":");

        return new DomainObject(Operations.valueOf(domainParts[0]),domainParts[1],
                BigDecimal.valueOf(Integer.valueOf(domainParts[2])));
    }
}
