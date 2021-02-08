package Day2.Demo1TCP.ClientServer;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class SimpleSocketClient {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 1286);
            InputStream socketInputStream = socket.getInputStream();
            DataInputStream socketDIS = new DataInputStream(socketInputStream);

            String testingString = socketDIS.readUTF();
            System.out.println(testingString);

            socketDIS.close();
//            socketInputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
