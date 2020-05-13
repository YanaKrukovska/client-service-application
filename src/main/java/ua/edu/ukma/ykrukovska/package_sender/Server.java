package ua.edu.ukma.ykrukovska.package_sender;

import java.nio.ByteBuffer;

public class Server {

    private byte[] getPackageHeaderBytes(Package pack) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(14);
        byteBuffer.put(pack.getMagic());
        byteBuffer.put(pack.getSrc());
        byteBuffer.putLong(pack.getPktId());
        byteBuffer.putInt(pack.getLen());
        return byteBuffer.array();
    }

    private byte[] turnMessageToByteArray(Message message) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8 + message.getMessage().length());
        byteBuffer.putInt(message.getType());
        byteBuffer.putInt(message.getUserId());
        for (byte b : message.getMessage().getBytes()) {
            byteBuffer.put(b);
        }
        return byteBuffer.array();
    }

    private byte[] convertPackageToBytes(Package pack) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(18 + pack.getLen());
        for (byte b : getPackageHeaderBytes(pack)) {
            byteBuffer.put(b);
        }
        byteBuffer.putShort(pack.getCrc16_1());

        for (byte b : turnMessageToByteArray(pack.getMsq())) {
            byteBuffer.put(b);
        }
        byteBuffer.putShort(pack.getCrc16_2());

        return byteBuffer.array();
    }

    public byte[] sendPackage(Package pack) {
        return convertPackageToBytes(pack);
    }

}
