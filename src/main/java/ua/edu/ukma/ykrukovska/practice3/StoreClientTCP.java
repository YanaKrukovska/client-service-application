package ua.edu.ukma.ykrukovska.practice3;

import ua.edu.ukma.ykrukovska.practice1.Package;
import ua.edu.ukma.ykrukovska.practice1.Server;

import java.io.*;
import java.net.Socket;

public class StoreClientTCP {

    private static int clientId = 0;
    private Socket clientSocket;
    private PrintWriter out;
    private DataOutputStream  outputStream;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            outputStream = new DataOutputStream (clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error when initializing connection");
        }
    }

    public void sendPackage(Package packageToSend) {
        try {
            byte[] message = new Server(clientId++).convertPackageToBytes(packageToSend);
            outputStream.writeInt(message.length);
            outputStream.write(message);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stopConnection() {
        try {
            out.close();
            outputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("error when closing");
        }

    }

    public int getClientId() {
        return clientId;
    }
}
