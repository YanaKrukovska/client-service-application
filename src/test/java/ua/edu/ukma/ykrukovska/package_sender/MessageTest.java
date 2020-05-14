package ua.edu.ukma.ykrukovska.package_sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MessageTest {

    @Test
    void encryptSimpleMessage() {
        Message message = new Message.MessageBuilder().type(1).userId(2).message("Rita").build();
        byte[] encryptedMessage = message.encryptMessage(message);
        Assertions.assertEquals(new String(Client.decryptMessage(encryptedMessage)), message.getMessage());
    }

    @Test
    void encryptLongMessage() {
        Message message = new Message.MessageBuilder().type(1).userId(2).message("Rita is my cat").build();
        byte[] encryptedMessage = message.encryptMessage(message);
        Assertions.assertEquals(new String(Client.decryptMessage(encryptedMessage)), message.getMessage());
    }

    @Test
    void encryptMessageWithNumbers() {
        Message message = new Message.MessageBuilder().type(1).userId(2).message("Rita is 15 years old").build();
        byte[] encryptedMessage = message.encryptMessage(message);
        Assertions.assertEquals(new String(Client.decryptMessage(encryptedMessage)), message.getMessage());
    }

    @Test
    void encryptMessageWithSymbols() {
        Message message = new Message.MessageBuilder().type(1).userId(2).message("Rita is 15 years old!").build();
        byte[] encryptedMessage = message.encryptMessage(message);
        Assertions.assertEquals(new String(Client.decryptMessage(encryptedMessage)), message.getMessage());
    }

}