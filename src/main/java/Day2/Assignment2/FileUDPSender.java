package Day2.Assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class FileUDPSender {
    public static void main(String[] args) {
        DatagramSocket dgSocket = null;
        try {
            dgSocket = new DatagramSocket(3000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                byte[] byteBuffer = new byte[1000];
                DatagramPacket dgRequest = new DatagramPacket(byteBuffer, byteBuffer.length);
                dgSocket.receive(dgRequest);
                System.out.println(new String(dgRequest.getData()));

//                File file = new File("G:\\JETS\\IO and Network Programming\\Day2\\src\\main\\java\\Day1\\Assignment2\\FileUDPSender.java");
//                FileReader fileReader = new FileReader(file);
                FileReader fileReader = new FileReader("FileUDPReceiver.java");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while (true) {
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        DatagramPacket dgResponse = new DatagramPacket(line.getBytes(StandardCharsets.UTF_8),
                                line.getBytes(StandardCharsets.UTF_8).length, dgRequest.getAddress(), dgRequest.getPort());
                        dgSocket.send(dgResponse);
                    } else {
                        DatagramPacket dgResponse = new DatagramPacket("END".getBytes(StandardCharsets.UTF_8),
                                "END".getBytes(StandardCharsets.UTF_8).length, dgRequest.getAddress(), dgRequest.getPort());
                        dgSocket.send(dgResponse);
                        break;
                    }
                    System.out.println(line);
                    Thread.sleep(200);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
