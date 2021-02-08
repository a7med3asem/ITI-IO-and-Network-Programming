package Day2.Demo1TCP.MultipleClientOneServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadedEchoServer {
    public static void main(String[] args) {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(8199);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 1;

        while (true) {
            try {
                Socket incoming = socket.accept();
                System.out.println("Spawning " + i);

                Runnable runnable = new ThreadedEchoHandler(incoming);
                Thread thread = new Thread(runnable);
                thread.start();
                i++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
