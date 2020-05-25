package ua.edu.ukma.ykrukovska.practice2.worker;

import ua.edu.ukma.ykrukovska.practice2.channel.Channel;
import ua.edu.ukma.ykrukovska.practice2.messages.Message;
import ua.edu.ukma.ykrukovska.practice2.messages.InsecureMessage;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DecipherWorker extends Worker<Message, InsecureMessage> {

    private static int workerCounter = 0;

    public DecipherWorker(Channel<Message> inboundChannel, Channel<InsecureMessage> outboundChannel) {
        super(inboundChannel, outboundChannel);
        id = this.getClass().getSimpleName() + ":" + ++workerCounter;
    }

    @Override
    protected InsecureMessage process(Message inputData) {
        return new InsecureMessage(inputData.getType(), inputData.getUserId(),
                new String(decryptMessage(inputData.getEncryptedMessage())));
    }

    private byte[] decryptMessage(byte[] byteMessage) {
        Cipher cipher = null;
        byte[] decryptedMessageBytes = new byte[0];
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            String encryptionKeyString = "thisisa128bitkey";
            byte[] encryptionKeyBytes = encryptionKeyString.getBytes();
            SecretKey secretKey = new SecretKeySpec(encryptionKeyBytes, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptedMessageBytes = cipher.doFinal(byteMessage);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return decryptedMessageBytes;
    }

}
