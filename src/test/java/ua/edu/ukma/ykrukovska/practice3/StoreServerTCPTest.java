package ua.edu.ukma.ykrukovska.practice3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.edu.ukma.ykrukovska.practice1.Message;
import ua.edu.ukma.ykrukovska.practice1.Package;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

class StoreServerTCPTest {

    private static int port;

    @BeforeAll
    public static void setServer() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(0);
        port = serverSocket.getLocalPort();
        serverSocket.close();

        Executors.newSingleThreadExecutor().submit(() -> new StoreServerTCP().start(port));
        Thread.sleep(500);
    }


    @Test
    public void sendPackageClient1(){
        StoreClientTCP client = new StoreClientTCP();
        client.startConnection("127.0.0.1", port);

        Message message = new Message.MessageBuilder().type(3).userId(4).message("Rita").build();
        Package packageStart = new Package.PackageBuilder().pktId().src((byte) client.getClientId()).len(message.size()).msq(message).crc16_1().crc16_2().build();
        client.sendPackage(packageStart);
        client.stopConnection();

    }

    @Test
    public void sendPackageClient2(){
        StoreClientTCP client = new StoreClientTCP();
        client.startConnection("127.0.0.1", port);

        Message message = new Message.MessageBuilder().type(1).userId(2).message("Bender").build();
        Package packageStart = new Package.PackageBuilder().pktId().src((byte) client.getClientId()).len(message.size()).msq(message).crc16_1().crc16_2().build();
        client.sendPackage(packageStart);
        client.stopConnection();
    }

    @Test
    public void sendPackageClient3(){
        StoreClientTCP client = new StoreClientTCP();
        client.startConnection("127.0.0.3", port);

        Message message = new Message.MessageBuilder().type(0).userId(1).message("Ricardo Milos").build();
        Package packageStart = new Package.PackageBuilder().pktId().src((byte) client.getClientId()).len(message.size()).msq(message).crc16_1().crc16_2().build();
        client.sendPackage(packageStart);
        client.stopConnection();
    }

}