package ua.edu.ukma.ykrukovska.practice3;

import ua.edu.ukma.ykrukovska.practice1.Client;
import ua.edu.ukma.ykrukovska.practice1.Package;
import ua.edu.ukma.ykrukovska.practice1.Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class StoreClientUDP {

    private DatagramSocket socket;
    private InetAddress address;
    private boolean isSuccess = false;
    private static int port = 8081;
    private static int clientId = 0;

    public StoreClientUDP() {
        try {
            socket = new DatagramSocket();
            address = InetAddress.getByName("localhost");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendPackage(Package packageToSend) {
        DatagramPacket packet = null;
        try {
            while (!isSuccess) {
                byte[] buf = new Server(clientId++).convertPackageToBytes(packageToSend);

                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);

                byte[] byteBufferCheck = new byte[256];
                DatagramPacket checkIfWasSent = new DatagramPacket(byteBufferCheck, byteBufferCheck.length);
                socket.receive(checkIfWasSent);
                Package packageReceived = new Client().receivePackage(checkIfWasSent.getData());
                if (packageReceived.getCrc16_1() == packageToSend.getCrc16_1() && packageReceived.getCrc16_2() == packageToSend.getCrc16_2()) {
                    isSuccess = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getClientId() {
        return clientId;
    }

}



