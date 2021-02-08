package Day3.Example2.ExplicitChannelRead;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

public class ExplicitChannelRead {
    public static void main(String[] args) {
        // to indicate the end of the file
        int count;
        try {
            // First, obtain a path to the already created file.
            Path filepath = Paths.get("src/main/resources/Test.txt");
            // Next, obtain a channel to that file within a try-with-resources block.
            try (SeekableByteChannel fileChannel = Files.newByteChannel(filepath)) {
                // Allocate a buffer.
                ByteBuffer messageBuffer = ByteBuffer.allocate(128);
                do {
                    // Read a buffer.
                    count = fileChannel.read(messageBuffer);
                    // Stop when end of file is reached.
                    if (count != -1) {
                        // Rewind the buffer so that it can be read.
                        messageBuffer.rewind();
                        // Read bytes from the buffer and show them on the screen as characters.
                        for (int i = 0 ; i < count ; i++)
                            System.out.print((char) messageBuffer.get());
                    }
                } while (count != -1);
                System.out.println();
            } catch (InvalidPathException e) {
                System.out.println("Path Error " + e);
            }
        } catch (IOException e) {
            System.out.println("I/O Error " + e);
        }
    }
}
