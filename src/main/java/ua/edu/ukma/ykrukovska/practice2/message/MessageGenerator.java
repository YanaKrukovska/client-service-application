package ua.edu.ukma.ykrukovska.practice2.message;

import ua.edu.ukma.ykrukovska.practice2.Receiver;

import java.util.Random;
import java.util.UUID;

public class MessageGenerator {

    private Receiver receiver;

    public MessageGenerator(Receiver receiver) {
        this.receiver = receiver;
    }

    public void sendMessages(int amount){
        for (int i = 0; i < amount; i++) {
            receiver.receiveMessage(generateMessage());
        }
    }

    public Message generateMessage() {
        return new Message.MessageBuilder().message(randomMessage()).type(0).userId(new Random().nextInt()).build();
    }

    private String randomMessage() {
        return UUID.randomUUID().toString();
    }
}
