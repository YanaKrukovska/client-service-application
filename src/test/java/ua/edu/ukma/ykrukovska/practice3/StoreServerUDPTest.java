package ua.edu.ukma.ykrukovska.practice3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ua.edu.ukma.ykrukovska.practice1.Message;
import ua.edu.ukma.ykrukovska.practice1.Package;

class StoreServerUDPTest {

    private static int port = 8081;

    @BeforeAll
    public static void setServer(){
        new StoreServerUDP().start(port);
    }


    @Test
    public void sendPackageClient1(){
        StoreClientUDP client = new StoreClientUDP();
        Message message = new Message.MessageBuilder().type(2).userId(5).message("Rita").build();
        Package packageStart = new Package.PackageBuilder().pktId().src((byte) client.getClientId()).len(message.size()).msq(message).crc16_1().crc16_2().build();
        client.sendPackage(packageStart);
    }

    @Test
    public void sendPackageClient2(){
        StoreClientUDP client = new StoreClientUDP();
        Message message = new Message.MessageBuilder().type(1).userId(8).message("Ricardo Milos").build();
        Package packageStart = new Package.PackageBuilder().pktId().src((byte) client.getClientId()).len(message.size()).msq(message).crc16_1().crc16_2().build();
        client.sendPackage(packageStart);
    }

    @Test
    public void sendPackageClient3(){
        StoreClientUDP client = new StoreClientUDP();
        Message message = new Message.MessageBuilder().type(3).userId(2).message("Bender").build();
        Package packageStart = new Package.PackageBuilder().pktId().src((byte) client.getClientId()).len(message.size()).msq(message).crc16_1().crc16_2().build();
        client.sendPackage(packageStart);
    }

    @AfterAll
    public static void stopServer(){
        StoreClientUDP client = new StoreClientUDP();
        Message message = new Message.MessageBuilder().type(0).userId(0).message("end").build();
        Package packageStart = new Package.PackageBuilder().pktId().src((byte) client.getClientId()).len(message.size()).msq(message).crc16_1().crc16_2().build();
        client.sendPackage(packageStart);
    }


}