package ua.edu.ukma.ykrukovska.practice2.messages;

import ua.edu.ukma.ykrukovska.practice2.domain.Operations;

import java.util.Random;

public class MessageCreatorImpl implements MessageCreator<Message> {
    @Override
    public Message create() {
        return new Message.MessageBuilder().message(randomMessage()).type(0).userId(new Random().nextInt()).build();
    }

    private String randomMessage() {
        return Operations.values()[new Random().nextInt(Operations.values().length)] + ":"
                + "Product " + new Random().nextInt(10)+ ":"
                + new Random().nextInt(1000);
    }
}
