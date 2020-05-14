package ua.edu.ukma.ykrukovska.package_sender;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Message {

    private int type;
    private int userId;
    private String message;
    private byte[] encryptedMessage;

    public Message(MessageBuilder builder) {
        this.type = builder.type;
        this.userId = builder.userId;
        this.message = builder.message;
        this.encryptedMessage = encryptMessage(this);
    }

    public byte[] encryptMessage(Message message) {

        Cipher cipher = null;
        byte[] encryptedMessageBytes = new byte[0];
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            String encryptionKeyString = "thisisa128bitkey";
            byte[] encryptionKeyBytes = encryptionKeyString.getBytes();
            SecretKey secretKey = new SecretKeySpec(encryptionKeyBytes, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedMessageBytes = cipher.doFinal(message.getMessage().getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return encryptedMessageBytes;
    }

    public int size() {
        return 8 + encryptedMessage.length;
    }

    public int getType() {
        return type;
    }

    public int getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public byte[] getEncryptedMessage() {
        return encryptedMessage;
    }

    @Override
    public String toString() {
        return "Message (" + "type = " + type +
                ", userId = " + userId +
                ", message = " + message + ")";
    }

    public static final class MessageBuilder {
        private int type;
        private int userId;
        private String message;

        public MessageBuilder() {
        }

        public MessageBuilder type(int type) {
            this.type = type;
            return this;
        }

        public MessageBuilder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public MessageBuilder message(String message) {
            this.message = message;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
