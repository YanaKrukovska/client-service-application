package ua.edu.ukma.ykrukovska.practice3;

import ua.edu.ukma.ykrukovska.practice1.Client;
import ua.edu.ukma.ykrukovska.practice1.Package;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StoreServerTCP {

    private ServerSocket serverSocket;

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                new StoreClientTCPWorker(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            stop();
        } finally {
            stop();
        }
    }

    private void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class StoreClientTCPWorker extends Thread {
        private Socket clientSocket;
        private DataInputStream inputStream;
        private static int counter = 0;

        public StoreClientTCPWorker(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                System.out.println("Started worker #" + counter++);
                inputStream = new DataInputStream(clientSocket.getInputStream());

                byte[] response = new byte[0];
                int length = inputStream.readInt();
                if (length > 0) {
                    response = new byte[length];
                    inputStream.readFully(response, 0, response.length);
                }
                Package receivedPackage = new Client().receivePackage(response);
                System.out.println(receivedPackage.toString());

                inputStream.close();
                clientSocket.close();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
