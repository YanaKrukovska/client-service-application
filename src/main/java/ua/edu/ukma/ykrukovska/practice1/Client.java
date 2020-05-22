package ua.edu.ukma.ykrukovska.practice1;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Client {

    public Client() {
    }

    public static Message decryptMessageFromByteArray(byte[] bytes) {
        int type = ByteBuffer.wrap(bytes, 0, 4).getInt();
        int userId = ByteBuffer.wrap(bytes, 4, 4).getInt();
        byte[] byteMessage = Arrays.copyOfRange(bytes, 8, bytes.length);

        String message = new String(decryptMessage(byteMessage));

        return new Message(new Message.MessageBuilder().type(type).userId(userId).message(message));
    }

    public static byte[] decryptMessage(byte[] byteMessage) {
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


    public Package receivePackage(byte[] receivedPackage) {

        byte src = ByteBuffer.wrap(receivedPackage, 1, 1).get();
        long id = ByteBuffer.wrap(receivedPackage, 2, 8).getLong();
        int len = ByteBuffer.wrap(receivedPackage, 10, 4).getInt();
        byte[] headerBytes = Arrays.copyOfRange(receivedPackage, 0, 14);
        short crc1 = ByteBuffer.wrap(receivedPackage, 14, 2).getShort();
        if (crc1 != CRC16.getCRC(headerBytes)) {
            throw new IllegalArgumentException("Wrong crc1");
        }

        byte[] byteMessage = Arrays.copyOfRange(receivedPackage, 16, 16 + len);
        Message msg = decryptMessageFromByteArray(byteMessage);
        short crc2 = ByteBuffer.wrap(receivedPackage, 16 + len + 8, 2).getShort();

        if (crc2 != CRC16.getCRC(msg.getMessage())) {
            throw new IllegalArgumentException("Wrong crc2");
        }


        return new Package(new Package.PackageBuilder().src(src).pktId(id).len(len).crc16_1(crc1).crc16_2(crc2).msq(msg));
    }


}
