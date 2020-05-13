package ua.edu.ukma.ykrukovska.package_sender;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class Client {

    public Client() {
    }

    public static Message getMessageFromByteArray(byte[] bytes) {
        int type = ByteBuffer.wrap(bytes, 0, 4).getInt();
        int userId = ByteBuffer.wrap(bytes, 4, 4).getInt();
        byte[] byteMessage = Arrays.copyOfRange(bytes, 8, bytes.length);
        String message = new String(byteMessage);
        return new Message(new Message.MessageBuilder().type(type).userId(userId).message(message));
    }

    public Package receivePackage(byte[] receivedPackage) {

        byte src = ByteBuffer.wrap(receivedPackage, 1, 1).get();
        long id = ByteBuffer.wrap(receivedPackage, 2, 8).getLong();
        int len = ByteBuffer.wrap(receivedPackage, 10, 4).getInt();

        short crc1 = ByteBuffer.wrap(receivedPackage, 14, 2).getShort();

        byte[] byteMessage = Arrays.copyOfRange(receivedPackage, 16, 16 + len);
        short crc2 = ByteBuffer.wrap(receivedPackage, 16 + len, 2).getShort();

        Message msg = getMessageFromByteArray(byteMessage);
        return new Package(new Package.PackageBuilder().src(src).pktId(id).len(len).crc16_1(crc1).crc16_2(crc2).msq(msg));
    }


}
