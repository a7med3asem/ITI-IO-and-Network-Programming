package Day3.Example4.ExplicitChannelWrite;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

public class ExplicitChannelWrite {
    public static void main(String[] args) {
        // Obtain a channel to a file within a try-with-resources block.
        try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(Paths.get("src/main/resources/Test.txt"),
                StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            // Create a buffer with size 26 bytes
            ByteBuffer byteBuffer = ByteBuffer.allocate(26);
            // append alphabetic characters
            for (int i = 0; i < 26; i++)
                byteBuffer.put((byte) ('A' + i));
            // Reset the buffer so that it can be written or read from first.
            byteBuffer.rewind();
            // Write the buffer to the output file.
            fileChannel.write(byteBuffer);
            // close file channel
//            fileChannel.close();
        } catch (InvalidPathException e) {
            System.out.println("Path Error " + e);
        } catch (IOException e) {
            System.out.println("I/O Error: " + e);
            System.exit(1);
        }
    }
}