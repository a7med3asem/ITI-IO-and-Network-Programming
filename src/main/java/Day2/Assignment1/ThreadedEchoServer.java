package Day2.Assignment1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ThreadedEchoServer {
    public static List<ThreadedEchoHandler> clients;

    public static void main(String[] args) {
        ServerSocket socket = null;
        clients = new ArrayList<>();
        try {
            socket = new ServerSocket(8198);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 1;

        while (true) {
            try {
                assert socket != null;
                Socket incoming = socket.accept();
                System.out.println("Spawning " + i);

                ThreadedEchoHandler threadedEchoHandler = new ThreadedEchoHandler(incoming);
                threadedEchoHandler.start();
                clients.add(threadedEchoHandler);
                i++;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void broadCast(String msg) {
        for (int i = 0 ; i < clients.size() ; i++) {
            ThreadedEchoHandler th = clients.get(i);
            if (th.isAlive()) {
                th.out.println(msg);
            } else {
                clients.remove(i);
            }
        }
    }
}
