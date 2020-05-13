package ua.edu.ukma.ykrukovska.package_sender;

public class Package {

    private final byte magic = 0xD;
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

    /*
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        String encryptionKeyString = "thisisa128bitkey";
        String originalMessage = "This is a secret message";
        byte[] encryptionKeyBytes = encryptionKeyString.getBytes();
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(encryptionKeyBytes, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedMessageBytes = cipher.doFinal(originalMessage.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedMessageBytes = cipher.doFinal(encryptedMessageBytes);
        System.out.println(new String(decryptedMessageBytes));

    }*/

    public Package(PackageBuilder packageBuilder) {
        this.src = packageBuilder.src;
        this.pktId = packageBuilder.pktId;
        this.len = packageBuilder.len;
        this.crc16_1 = packageBuilder.crc16_1;
        this.msq = packageBuilder.msq;
        this.crc16_2 = packageBuilder.crc16_2;
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

        public PackageBuilder src(byte src) {
            this.src = src;
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

        public PackageBuilder crc16_1(short crc16_1) {
            this.crc16_1 = crc16_1;
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
