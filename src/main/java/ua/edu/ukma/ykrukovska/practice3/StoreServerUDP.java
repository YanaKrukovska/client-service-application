package ua.edu.ukma.ykrukovska.practice3;

import ua.edu.ukma.ykrukovska.practice1.Client;
import ua.edu.ukma.ykrukovska.practice1.Package;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class StoreServerUDP {

    private static boolean isRunning = true;
    private static final int WORKER_AMOUNT = 3;

    public void start(int port) {
        try {
            DatagramSocket socket = new DatagramSocket(port);
            for (int i = 0; i < WORKER_AMOUNT; i++) {
                new StoreServerUDP.StoreClientUDPWorker(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static class StoreClientUDPWorker extends Thread {
        private DatagramSocket socket;
        private static int counter = 0;
        private int number;
        public byte[] byteBuffer = new byte[256];

        public StoreClientUDPWorker(DatagramSocket socket) {
            this.number = counter++;
            this.socket = socket;
        }

        public void run() {

            while (isRunning) {
                try {
                    DatagramPacket packet = new DatagramPacket(byteBuffer, byteBuffer.length);
                    socket.receive(packet);
                    Package packageReceived = new Client().receivePackage(packet.getData());
                    System.out.println("Worker #" + number + " received: " + packageReceived.toString());

                    socket.send(packet);

                    if (packageReceived.getMsq().getMessage().equals("end")) {
                        isRunning = false;
                    }

                } catch (IOException e) {
                    isRunning = false;
                }
            }
            socket.close();

        }
    }
}



