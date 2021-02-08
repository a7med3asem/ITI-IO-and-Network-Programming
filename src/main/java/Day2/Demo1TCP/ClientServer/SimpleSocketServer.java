package Day2.Demo1TCP.ClientServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
* Reading from the socket - Server
*/

public class SimpleSocketServer {
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(1286);
            Socket socket = serverSocket.accept();

            OutputStream socketOutStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(socketOutStream);
            dataOutputStream.writeUTF("Hello World!");

            dataOutputStream.close();
            socketOutStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
