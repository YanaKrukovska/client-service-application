package ua.edu.ukma.ykrukovska;

import org.junit.jupiter.api.Test;
import ua.edu.ukma.ykrukovska.practice2.WatchCat;
import ua.edu.ukma.ykrukovska.practice2.channel.Channel;
import ua.edu.ukma.ykrukovska.practice2.channel.ConcurrentChannel;
import ua.edu.ukma.ykrukovska.practice2.domain.DomainObject;
import ua.edu.ukma.ykrukovska.practice2.messages.*;
import ua.edu.ukma.ykrukovska.practice2.processor.*;

public class TestRunner {

    /*Due to JUnit implementation it terminates threads before they are finished. To see
    * the proper work of the following code please check DemoApplication*/


    @Test
    void runApplication() throws InterruptedException {
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

        WatchCat watchCat = new WatchCat();
        watchCat.registerChannel(inbound);
        watchCat.registerChannel(decipheredChannel);
        watchCat.registerChannel(domainChannel);
        watchCat.registerChannel(insecureResponsesChannel);
        watchCat.registerChannel(encriptedChannel);

        watchCat.registerProcessor(decipherService);
        watchCat.registerProcessor(messageProcessor);
        watchCat.registerProcessor(messageHandler);
        watchCat.registerProcessor(encriptorProcessor);
        watchCat.registerProcessor(fakeMessageSender);

        Thread monitorChannels = new Thread(watchCat);
        monitorChannels.start();

    }

}