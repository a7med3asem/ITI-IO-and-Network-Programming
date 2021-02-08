package Day2.Assignment2;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class FileUDPReceiver {
    public static void main(String[] args) {
        // Try with datagram socket as resource
        try (DatagramSocket dgSocket = new DatagramSocket()) {
            // Initial message string to send as request
            String msg = "Hello World!";
            // Convert string to byte to be able to be sent in a datagram
            byte[] bytes = msg.getBytes();
            // Create Internet address of localhost as sender host ip
            InetAddress inetAddress = InetAddress.getLocalHost();
            // Receiver port number
            int serverPortNumber = 3000;
            // Create datagram packet to send request as udp message
            DatagramPacket dgRequest = new DatagramPacket(bytes, msg.length(), inetAddress, serverPortNumber);
            // send the packet
            dgSocket.send(dgRequest);

            // byte buffer to receive response
            byte[] byteBuffer = new byte[1000];

            while (true) {
                // new Datagram to receive response in it
                DatagramPacket dgResponse = new DatagramPacket(byteBuffer, byteBuffer.length);
                dgSocket.receive(dgResponse);
                String str = new String(dgResponse.getData(), dgResponse.getOffset(), dgResponse.getLength());
                // print datagram response content on console
                if (str.equals("END"))
                    break;
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
