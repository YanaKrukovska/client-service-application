package ua.edu.ukma.ykrukovska.practice2;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;
import ua.edu.ukma.ykrukovska.practice2.channel.ConcurrentChannel;
import ua.edu.ukma.ykrukovska.practice2.domain.DomainObject;
import ua.edu.ukma.ykrukovska.practice2.messages.*;
import ua.edu.ukma.ykrukovska.practice2.processor.*;

public class DemoApplication {


    public static void main(String[] args) throws InterruptedException {
        Channel<Message> inbound = new ConcurrentChannel<>();

        FakeMessageService<Message> messageGenerator = new FakeMessageService<>(inbound, new MessageCreatorImpl());
        messageGenerator.generateMessages();
        ProcessingManager.startProcessing();

        Channel<InsecureMessage> decipheredChannel = new ConcurrentChannel<>();
        DecipherService decipherService = new DecipherService(inbound, decipheredChannel);
        decipherService.startProcessing();

        Channel<DomainObject> domainChannel = new ConcurrentChannel<>();
        MessageProcessor messageProcessor = new MessageProcessor(decipheredChannel, domainChannel);
        messageProcessor.startProcessing();

        Channel<InsecureMessage> insecureResponsesChannel = new ConcurrentChannel<>();
        MessageHandler messageHandler = new MessageHandler(domainChannel, insecureResponsesChannel);
        messageHandler.startProcessing();

        Channel<Message> encriptedChannel = new ConcurrentChannel<>();
        EncriptorProcessor encriptorProcessor = new EncriptorProcessor(insecureResponsesChannel, encriptedChannel);
        encriptorProcessor.startProcessing();

        FakeMessageSender fakeMessageSender = new FakeMessageSender(encriptedChannel, null);
        fakeMessageSender.startProcessing();

    }
}
