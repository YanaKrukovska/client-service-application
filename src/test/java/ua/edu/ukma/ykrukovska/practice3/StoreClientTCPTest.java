package ua.edu.ukma.ykrukovska.practice3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class StoreClientTCPTest {

    private static int port;

    @BeforeAll
    public static void start() throws InterruptedException, IOException {
        ServerSocket serverSocket = new ServerSocket(0);
        port = serverSocket.getLocalPort();
        serverSocket.close();

        Executors.newSingleThreadExecutor().submit(() -> new StoreServerTCP().start(port));
        Thread.sleep(500);
    }

    @Test
    public void givenClient1_whenServerResponds_thenCorrect() {
        StoreClientTCP client = new StoreClientTCP();
        client.startConnection("127.0.0.1", port);
        String message1 = client.sendMessage("cat");
        String message2 = client.sendMessage("meow");
        String terminate = client.sendMessage(".");

        assertEquals(message1, "cat");
        assertEquals(message2, "meow");
        assertEquals(terminate, "bye");
        client.stopConnection();
    }

    @Test
    public void givenClient2_whenServerResponds_thenCorrect() {
        StoreClientTCP client = new StoreClientTCP();
        client.startConnection("127.0.0.1", port);
        String message1 = client.sendMessage("hello");
        String message2 = client.sendMessage("kitty");
        String terminate = client.sendMessage(".");
        assertEquals(message1, "hello");
        assertEquals(message2, "kitty");
        assertEquals(terminate, "bye");
        client.stopConnection();
    }

    @Test
    public void givenClient3_whenServerResponds_thenCorrect() {
        StoreClientTCP client = new StoreClientTCP();
        client.startConnection("127.0.0.1", port);
        String message1 = client.sendMessage("Bender");
        String message2 = client.sendMessage("Rodriguez");
        String terminate = client.sendMessage(".");
        assertEquals(message1, "Bender");
        assertEquals(message2, "Rodriguez");
        assertEquals(terminate, "bye");
        client.stopConnection();
    }

}