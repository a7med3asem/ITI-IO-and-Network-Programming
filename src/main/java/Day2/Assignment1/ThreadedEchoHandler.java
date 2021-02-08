package Day2.Assignment1;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedEchoHandler extends Thread{

    private Socket incoming;
    public PrintWriter out;

    public ThreadedEchoHandler(Socket incomingSocket){
        incoming = incomingSocket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = incoming.getInputStream();
            OutputStream outputStream = incoming.getOutputStream();
            Scanner in = new Scanner(inputStream, "UTF-8");

            out = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);

            out.println("Hello! Enter BYE to exit.");

            boolean done = false;

            while (!done) {
                if (in.hasNextLine()) {
                    String line = in.nextLine();
//                    out.println("Echo: " + line);
                    ThreadedEchoServer.broadCast(line);
                    if (line.trim().equals("BYE"))
                        done = true;
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
