package ua.edu.ukma.ykrukovska.package_sender;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Message {

    private int type;
    private int userId;
    private String message;


    public Message(MessageBuilder builder) {
        this.type = builder.type;
        this.userId = builder.userId;
        this.message = builder.message;
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
            try {
                Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                String encryptionKeyString = "thisisa128bitkey";
                byte[] encryptionKeyBytes = encryptionKeyString.getBytes();
                SecretKey secretKey = new SecretKeySpec(encryptionKeyBytes, "AES");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                byte[] encryptedMessageBytes = cipher.doFinal(message.getBytes());
                cipher.init(Cipher.DECRYPT_MODE, secretKey);
                byte[] decryptedMessageBytes = cipher.doFinal(encryptedMessageBytes);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | InvalidKeyException | BadPaddingException e) {
                e.printStackTrace();
            }

            this.message = message;
            return this;
        }

        public Message build() {
            return new Message(this);
        }
    }
}
