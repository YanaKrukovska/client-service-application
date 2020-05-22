package ua.edu.ukma.ykrukovska.practice1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CipherTest {


    @Test
    void checkIfCiphers() {
        Message message = new Message.MessageBuilder().type(1).userId(2).message("Meow").build();
        byte[] encryptedMessage = message.encryptMessage(message);
        Assertions.assertNotEquals(message.getMessage().getBytes(),encryptedMessage);
    }

    @Test
    void checkIfDeCiphers() {
        Message message = new Message.MessageBuilder().type(1).userId(2).message("Cat").build();
        byte[] encryptedMessage = message.encryptMessage(message);
        Assertions.assertNotEquals(encryptedMessage, Client.decryptMessage(encryptedMessage));
    }

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
        Message message = new Message.MessageBuilder().type(1).userId(2).message("Rita is 15 years old! :)").build();
        byte[] encryptedMessage = message.encryptMessage(message);
        Assertions.assertEquals(new String(Client.decryptMessage(encryptedMessage)), message.getMessage());
    }

}