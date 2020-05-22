package ua.edu.ukma.ykrukovska.practice1;

import java.nio.ByteBuffer;

public class Server {

    private byte clientId;

    public Server(int clientId) {
        this.clientId = (byte) clientId;
    }

    public byte getClientId() {
        return clientId;
    }

    private byte[] getPackageHeaderBytes(Package pack) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(14);
        byteBuffer.put(pack.getMagic());
        byteBuffer.put(clientId);
        byteBuffer.putLong(pack.getPktId());
        byteBuffer.putInt(pack.getLen());
        return byteBuffer.array();
    }

    private byte[] convertMessageToByteArray(Message message) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8 + message.size());
        byteBuffer.putInt(message.getType());
        byteBuffer.putInt(message.getUserId());

        for (byte decryptedMessageByte : message.getEncryptedMessage()) {
            byteBuffer.put(decryptedMessageByte);
        }

        return byteBuffer.array();
    }

    private byte[] convertPackageToBytes(Package pack) {

        byte[] encryptedMessage = convertMessageToByteArray(pack.getMsq());

        ByteBuffer byteBuffer = ByteBuffer.allocate(18 + encryptedMessage.length);
        for (byte b : getPackageHeaderBytes(pack)) {
            byteBuffer.put(b);
        }
        byteBuffer.putShort(pack.getCrc16_1());
        for (byte b : encryptedMessage) {
            byteBuffer.put(b);
        }
        byteBuffer.putShort(pack.getCrc16_2());

        return byteBuffer.array();
    }

    public byte[] sendPackage(Package pack) {
        return convertPackageToBytes(pack);
    }

}
