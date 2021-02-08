package Day3.Example1.BuffersExample;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class BuffersExample {
    public static void main(String[] args) throws IOException {
        // Create a path of already created file to work with it
        Path path = Paths.get("src/main/resources/Test.txt");
        // Write a message to that file
        write(path);
        // read file content
        read(path);
    }

    private static void write(Path path) throws IOException {
        // message text content
        String messageText = "NIO Buffer Hello World!";
        // convert to byte array to send
        byte[] inputBytes = messageText.getBytes();
        // Wrapping -> allocating byte buffer with the byte array
        ByteBuffer byteBuffer = ByteBuffer.wrap(inputBytes);
        // create a channel
        FileChannel channelWrite = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        // write on file
        channelWrite.write(byteBuffer);
        // Close channel
        channelWrite.close();
    }

    // Reading from file
    private static void read(Path path) throws IOException {
        FileChannel channelRead = FileChannel.open(path);
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        channelRead.read(byteBuffer);
        byte[] byteArray = byteBuffer.array();
        String fileContent = new String(byteArray).trim();
        System.out.println("File Content: " + fileContent);
        channelRead.close();
    }
}