package ua.edu.ukma.ykrukovska.practice1;

import java.nio.ByteBuffer;

public class Package {

    private final byte magic = 0xD;
    private static long messageId = 1;
    private byte src;
    private long pktId;
    private int len;
    private short crc16_1;
    private Message msq;
    private short crc16_2;

    public byte getMagic() {
        return magic;
    }

    public byte getSrc() {
        return src;
    }

    public long getPktId() {
        return pktId;
    }

    public int getLen() {
        return len;
    }

    public short getCrc16_1() {
        return crc16_1;
    }

    public Message getMsq() {
        return msq;
    }

    public short getCrc16_2() {
        return crc16_2;
    }

    public Package(PackageBuilder packageBuilder) {
        this.src = packageBuilder.src;
        this.pktId = packageBuilder.pktId;
        this.len = packageBuilder.len;
        this.msq = packageBuilder.msq;
        this.crc16_1 = packageBuilder.crc16_1;
        this.crc16_2 = packageBuilder.crc16_2;
    }


    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Package)) {
            return false;
        }

        Package other = (Package) obj;

        if (this.src != other.src) {
            return false;
        }
        if (this.len != other.len) {
            return false;
        }
        if (this.pktId != other.pktId) {
            return false;
        }
        if (this.crc16_1 != other.crc16_1) {
            return false;
        }
        if (this.crc16_2 != other.crc16_2) {
            return false;
        }
        return this.msq.equals(other.msq);
    }

    @Override
    public String toString() {
        return "Package: " + "magic = " + magic +
                ", src = " + src +
                ", pktId = " + pktId +
                ", len = " + len +
                ", crc16_1 = " + crc16_1 +
                ", msq = " + msq +
                ", crc16_2 = " + crc16_2;
    }

    public static final class PackageBuilder {

        private byte src;
        private long pktId;
        private int len;
        private short crc16_1;
        private Message msq;
        private short crc16_2;

        public PackageBuilder() {
        }

        private byte[] calculateHeaderBytes() {
            ByteBuffer byteBuffer = ByteBuffer.allocate(14);
            byteBuffer.put((byte) 0xD);
            byteBuffer.put(src);
            byteBuffer.putLong(pktId);
            byteBuffer.putInt(len);
            return byteBuffer.array();
        }

        public PackageBuilder src(byte src) {
            this.src = src;
            return this;
        }

        public PackageBuilder pktId() {
            this.pktId = messageId++;
            return this;
        }

        public PackageBuilder pktId(long pktId) {
            this.pktId = pktId;
            return this;
        }

        public PackageBuilder len(int len) {
            this.len = len;
            return this;
        }

        public PackageBuilder crc16_1() {
            this.crc16_1 = CRC16.getCRC(calculateHeaderBytes());
            return this;

        }

        public PackageBuilder crc16_1(short crc16_1) {
            this.crc16_1 = crc16_1;
            return this;

        }

        public PackageBuilder crc16_2() {
            this.crc16_2 = CRC16.getCRC(msq.getMessage());
            return this;
        }

        public PackageBuilder crc16_2(short crc16_2) {
            this.crc16_2 = crc16_2;
            return this;
        }

        public PackageBuilder msq(Message msq) {
            this.msq = msq;
            return this;
        }

        public Package build() {
            return new Package(this);
        }


    }
}
