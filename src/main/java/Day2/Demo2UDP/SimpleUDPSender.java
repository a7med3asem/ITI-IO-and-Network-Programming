package Day2.Demo2UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class SimpleUDPSender {
    public static void main(String[] args) {
        while (true) {
            try (DatagramSocket dgSocket = new DatagramSocket(3000)) {

                byte[] byteBuffer = new byte[1000];
                DatagramPacket dgRequest = new DatagramPacket(byteBuffer, byteBuffer.length);
                dgSocket.receive(dgRequest);
                System.out.println(new String(dgRequest.getData()));

                DatagramPacket dgResponse = new DatagramPacket(dgRequest.getData(), dgRequest.getLength(),
                        dgRequest.getAddress(), dgRequest.getPort());
                dgSocket.send(dgResponse);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
