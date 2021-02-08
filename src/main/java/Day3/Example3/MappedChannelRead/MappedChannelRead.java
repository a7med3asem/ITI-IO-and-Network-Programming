package Day3.Example3.MappedChannelRead;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

public class MappedChannelRead {
    public static void main(String[] args) {
        // Obtain a channel to a file within a try-with-resources block.
        try(FileChannel fileChannel = (FileChannel) Files.newByteChannel(Paths.get("src/main/resources/Test.txt"))) {
            // Get the size of the file.
            long fileSize = fileChannel.size();
            // Now, map the file into a buffer.
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileSize);
            // Read and display bytes from buffer.
            for(int i = 0 ; i < fileSize ; i++)
                System.out.print((char) mappedByteBuffer.get());
            System.out.println();
        } catch(InvalidPathException e) {
            System.out.println("Path Error " + e);
        } catch(IOException e) {
            System.out.println("I/O Error " + e);
        }
    }
}
