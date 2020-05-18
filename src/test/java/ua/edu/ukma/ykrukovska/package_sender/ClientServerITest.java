package ua.edu.ukma.ykrukovska.package_sender;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class ClientServerITest {

    private static Client client;
    private static Server server;
    private static Message message;

    @BeforeAll
    static void setClientServer() {
        client = new Client();
        server = new Server(0);
        message = new Message.MessageBuilder().type(0).userId(1).message("Rita").build();
    }

    @Test
    void sendPackage() {

        Package packageSource = new Package.PackageBuilder().pktId().src(server.getClientId()).len(message.size()).msq(message).crc16_1().crc16_2().build();
        Package packageResult = client.receivePackage(server.sendPackage(packageSource));

        assertThat(packageSource).isEqualTo(packageResult);
    }

    @Test
    void receivePackageDifferentServer() {

        Message message = new Message.MessageBuilder().type(0).userId(1).message("Rita").build();
        Package packageSource = new Package.PackageBuilder().pktId().src(server.getClientId()).len(message.size()).msq(message).crc16_1().crc16_2().build();
        Package packageOther = new Package.PackageBuilder().pktId().src((byte) 1).len(message.size()).msq(message).crc16_1().crc16_2().build();
        Package packageResult = client.receivePackage(server.sendPackage(packageSource));

        assertThat(packageResult).isNotEqualTo(packageOther);
    }

    @Test
    void throwsExceptionWrongCRC1() {

        Package packageSource = new Package.PackageBuilder().pktId().src(server.getClientId()).len(message.size()).msq(message).crc16_1((short) 2).crc16_2().build();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            client.receivePackage(server.sendPackage(packageSource));
        });

        String expectedMessage = "Wrong crc1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void throwsExceptionWrongCRC2() {

        Package packageSource = new Package.PackageBuilder().pktId().src(server.getClientId()).len(message.size()).msq(message).crc16_1().crc16_2((short) 2).build();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            client.receivePackage(server.sendPackage(packageSource));
        });

        String expectedMessage = "Wrong crc2";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
