package ua.edu.ukma.ykrukovska.package_sender;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ClientServerITest {

    @Test
    void sendPackage() {
        Client client = new Client();
        Server server = new Server(0);

        Message message = new Message.MessageBuilder().type(0).userId(1).message("Rita").build();
        Package packageSource = new Package.PackageBuilder().pktId().src(server.getClientId()).len(message.size()).msq(message).build();
        Package packageResult = client.receivePackage(server.sendPackage(packageSource));

        assertThat(packageSource).isEqualTo(packageResult);
    }

    @Test
    void receivePackageDifferentServer() {
        Client client = new Client();
        Server server = new Server(0);

        Message message = new Message.MessageBuilder().type(0).userId(1).message("Rita").build();
        Package packageSource = new Package.PackageBuilder().pktId().src(server.getClientId()).len(message.size()).msq(message).build();
        Package packageOther = new Package.PackageBuilder().pktId().src((byte) 1).len(message.size()).msq(message).build();
        Package packageResult = client.receivePackage(server.sendPackage(packageSource));

        assertThat(packageResult).isNotEqualTo(packageOther);
    }

}
