package Day3.Example6.AsynchronousClientServer;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AsynchronousServer {
    public static void main(String[] args) {
        try (AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open()) {
            server.bind(new InetSocketAddress("127.0.0.1", 1234));
            Future<AsynchronousSocketChannel> acceptedConnection = server.accept();
            AsynchronousSocketChannel client = acceptedConnection.get(10, TimeUnit.SECONDS);

            if ((client != null) && (client.isOpen())) {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                Future<Integer> readValue = client.read(buffer);
                readValue.get();
                System.out.println("Received from client: " + new String(buffer.array()).trim());
                buffer.flip();

                Thread.sleep(2000);

                String str = "I'm fine. Thank you!";
                Future<Integer> writeValue = client.write(ByteBuffer.wrap(str.getBytes()));
                writeValue.get();
                System.out.println("Writing back to client: " + str);

                buffer.clear();
            }
            assert client != null;
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}